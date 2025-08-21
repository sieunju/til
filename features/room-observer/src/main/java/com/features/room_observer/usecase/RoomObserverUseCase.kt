package com.features.room_observer.usecase

import com.features.room_observer.models.AccountBusEvent
import com.features.room_observer.models.ActionIntent
import com.features.room_observer.models.Goods
import com.features.room_observer.models.RoomObserverParams
import com.features.room_observer.models.State
import com.features.room_observer.repository.GoodsRepository
import com.features.room_observer.repository.LocalRepository
import com.hmju.core.rxbus.RxBus
import com.hmju.core.util.RxUtil
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
class RoomObserverUseCase @Inject constructor(
	private val localRepository: LocalRepository,
	private val goodsRepository: GoodsRepository
) {

	sealed interface WorkState {

		data object Skip : WorkState

		data class DbObserverState(
			val list: List<Goods>,
			val isContentsA: Boolean,
			val action: ActionIntent
		) : WorkState

		data class RemoteState(
			val list: List<Goods>,
			val maxCount: Int,
			val isContentsA: Boolean,
			val action: ActionIntent
		) : WorkState
	}

	private var backgroundDisposable: Disposable? = null
	private var currentAccount: AccountBusEvent? = null

	operator fun invoke(
		params: RoomObserverParams
	): Flowable<State> {
		return Flowable.combineLatest(
			accountObserver(),
			params.action()
		) { account, action ->
			Timber.d("Combine ${account}-${action}")
			if (account is AccountBusEvent.User) {
				if (action != ActionIntent.INIT) {
					params.sendAction(ActionIntent.INIT)
				}
				return@combineLatest account to action
			}
			return@combineLatest account to action
		}.switchMap { pair ->
			val account = pair.first
			val action = pair.second
			if (account is AccountBusEvent.User) return@switchMap Flowable.just(State.LogOut)
			if (account !is AccountBusEvent.Member) return@switchMap Flowable.just(State.LogOut)
			val loadingStream = if (action == ActionIntent.FORCE_REFRESH) {
				Flowable.just(State.Loading)
			} else {
				Flowable.empty<State>()
			}.cast(State::class.java)
			val workStream = Flowable.mergeDelayError(
				localTask(account.id, action),
				remoteTask(params, action)
			).map { state ->
				if (state is WorkState.RemoteState) return@map State.Skip
				if (state is WorkState.Skip) return@map State.Skip
				if (state !is WorkState.DbObserverState) throw IllegalArgumentException("Error")
				converterState(params, state)
			}
			return@switchMap loadingStream.concatWith(workStream)
		}
	}

	private fun accountObserver(): Flowable<AccountBusEvent> {
		return RxBus.behavior(AccountBusEvent::class.java)
			.doOnNext {
				currentAccount = it
				closeBgDisposable()
			}
	}

	private fun converterState(
		params: RoomObserverParams,
		state: WorkState.DbObserverState
	): State {
		if (state.action == ActionIntent.FORCE_REFRESH) return State.Skip
		if (state.action == ActionIntent.INIT &&
			state.list.isEmpty()
		) return State.Loading
		if (state.list.isEmpty()) return State.Empty
		return if (state.isContentsA) {
			val takeList = state.list
				.take(params.getContentsSize(state.action))
			State.AContents(
				list = takeList,
				hasMore = takeList.size < state.list.size
			)
		} else {
			State.BContents(list = state.list.take(3))
		}
	}

	private fun localTask(userId: String, action: ActionIntent): Flowable<WorkState> {
		return Flowable.combineLatest(
			localRepository.findAllUserIdObserver(userId)
				.filter { isMemberState() }
				.compose(RxUtil.flowableMergeIo()),
			localRepository.isContentsARx()
				.compose(RxUtil.singleMergeIo()).toFlowable()
		) { list, isContentsA ->
			WorkState.DbObserverState(list, isContentsA, action)
		}.cast(WorkState::class.java)
	}

	private fun remoteTask(
		params: RoomObserverParams,
		action: ActionIntent
	): Flowable<WorkState> {
		if (action != ActionIntent.INIT &&
			action != ActionIntent.FORCE_REFRESH
		) {
			return Flowable.just(WorkState.Skip)
		}
		return Single.zip(
			fetchGoods().compose(RxUtil.singleMergeIo()),
			goodsRepository.fetchMaxCount().compose(RxUtil.singleMergeIo()),
			goodsRepository.fetchIsContentsA().compose(RxUtil.singleMergeIo())
		) { list, maxCount, isContentsA ->
			return@zip WorkState.RemoteState(list, maxCount, isContentsA, action)
		}.doOnSuccess {
			bgGoodsUpdate(it)
			localRepository.saveContentsType(it.isContentsA)
			// API 콜 완료
			params.sendAction(ActionIntent.LOADED)
		}.cast(WorkState::class.java).toFlowable()
	}

	private fun fetchGoods(): Single<List<Goods>> {
		return goodsRepository.fetch(1)
			.delay(2000, TimeUnit.MILLISECONDS)
			.flatMap { list ->
				return@flatMap localRepository.replaceAll(list).map { list }
			}
	}

	@Synchronized
	private fun closeBgDisposable() {
		if (backgroundDisposable != null) {
			backgroundDisposable?.dispose()
			backgroundDisposable = null
		}
	}

	private fun isMemberState(): Boolean {
		return currentAccount is AccountBusEvent.Member
	}

	data class BgWorkData(
		val list: List<List<Goods>>
	)

	private fun bgGoodsUpdate(state: WorkState.RemoteState) {
		closeBgDisposable()
		backgroundDisposable = Single.create { emitter ->
			val list = state.list
				.filter { Random.nextBoolean() }
				// .filter { false }
				.chunked(state.maxCount)
			emitter.onSuccess(BgWorkData(list))
		}
			.filter { it.list.isNotEmpty() }
			.flatMapPublisher {
				Flowable.fromIterable(it.list)
					.concatMapSingle { chunk ->
						val apis = chunk.map {
							goodsRepository.fetchById(it.id).compose(RxUtil.singleMergeIo())
						}

						if (apis.isEmpty()) {
							Single.just(emptyList())
						} else {
							Single.zip(apis) { results ->
								results.filterIsInstance<Goods>()
							}
						}
					}
			}.map { list ->
				list.map {
					it.copy(
						title = it.title.plus("Update"),
						message = it.message.plus("Update")
					)
				}
			}
			.flatMap { localRepository.updateAll(it).toFlowable() }
			.compose(RxUtil.flowableMergeIo())
			.subscribe()
	}
}
