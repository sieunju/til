package com.features.room_observer.usecase

import com.features.room_observer.models.AccountBusEvent
import com.features.room_observer.models.ActionIntent
import com.features.room_observer.models.Goods
import com.features.room_observer.models.RoomObserverParams
import com.features.room_observer.models.State
import com.features.room_observer.repository.GoodsRepository
import com.features.room_observer.repository.LocalRepository
import com.hmju.core.rxbus.RxBus
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
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

	operator fun invoke(
		params: RoomObserverParams
	): Flowable<State> {
		return RxBus.behavior(AccountBusEvent::class.java)
			.doOnNext { closeBgDisposable() }
			.flatMap { accountEvent ->
				if (accountEvent is AccountBusEvent.User) return@flatMap handleLogout()
				if (accountEvent !is AccountBusEvent.Member) throw IllegalArgumentException("Error")
				return@flatMap handleAction(accountEvent.id, params)
			}
	}

	private fun handleAction(
		userId: String,
		params: RoomObserverParams
	): Flowable<State> {
		return params.observerAction().flatMap { action ->
			val loadingStream = if (action == ActionIntent.FORCE_REFRESH) {
				Flowable.just(State.Loading)
			} else {
				Flowable.empty<State>()
			}.cast(State::class.java)

			val workStream = Flowable.mergeDelayError(
				localTask(userId, action),
				remoteTask(action)
			).map { state ->
				if (state is WorkState.RemoteState) return@map State.Skip
				if (state is WorkState.Skip) return@map State.Skip
				if (state !is WorkState.DbObserverState) throw IllegalArgumentException("Error")
				converterState(params, state)
			}

			return@flatMap loadingStream.concatWith(workStream)
		}
	}

	private fun converterState(
		params: RoomObserverParams,
		state: WorkState.DbObserverState
	): State {
		if (state.action == ActionIntent.INIT &&
			state.list.isEmpty()
		) {
			return State.Loading
		}
		if (state.list.isEmpty()) return State.Empty
		if (state.isContentsA) {
			val takeList = state.list
				.take(params.getContentsSize(state.action))
			return State.AContents(
				list = takeList,
				hasMore = takeList.size < state.list.size
			)
		} else {
			return State.BContents(
				list = state.list.take(3)
			)
		}
	}

	private fun handleLogout(): Flowable<State> {
		return Flowable.just(State.LogOut)
	}

	private fun localTask(userId: String, action: ActionIntent): Flowable<WorkState> {
		return Flowable.combineLatest(
			localRepository.findAllUserIdObserver(userId),
			localRepository.isContentsA().toFlowable()
		) { list, isContentsA ->
			return@combineLatest WorkState.DbObserverState(list, isContentsA, action)
		}
	}

	private fun remoteTask(action: ActionIntent): Flowable<WorkState> {
		return if (action == ActionIntent.INIT || action == ActionIntent.FORCE_REFRESH) {
			Single.zip(
				fetchGoods(),
				goodsRepository.fetchMaxCount(),
				goodsRepository.fetchIsContentsA()
			) { list, maxCount, isContentsA ->
				return@zip WorkState.RemoteState(list, maxCount, isContentsA, action)
			}.doOnSuccess { bgGoodsUpdate(it) }
				.doOnSuccess { localRepository.saveContentsType(it.isContentsA) }
				.cast(WorkState::class.java)
				.toFlowable()
		} else {
			Flowable.just(WorkState.Skip)
		}
	}

	private fun fetchGoods(): Single<List<Goods>> {
		return Single.zip(
			goodsRepository.fetch(1).delay(3000,TimeUnit.MILLISECONDS),
			goodsRepository.fetch(2)
		) { goods1, goods2 ->
			goods1 + goods2
		}.flatMap { list ->
			return@flatMap localRepository.replaceAll(list).map { list }
		}
	}

	@Synchronized
	private fun closeBgDisposable() {
		if (backgroundDisposable != null) {
			Timber.d("작업 취소합니다.")
			backgroundDisposable?.dispose()
			backgroundDisposable = null
		}
	}

	data class BgWorkData(
		val list: List<List<Goods>>
	)

	private fun bgGoodsUpdate(state: WorkState.RemoteState) {
		closeBgDisposable()
		backgroundDisposable = Single.create { emitter ->
			val list = state.list
				.filter { Random.nextBoolean() }
				.chunked(state.maxCount)
			emitter.onSuccess(BgWorkData(list))
		}.flatMapPublisher {
			Flowable.fromIterable(it.list)
				.concatMapSingle { chunk ->
					val apis = chunk.map {
						goodsRepository.fetchById(it.id).subscribeOn(Schedulers.io())
					}

					if (apis.isEmpty()) {
						Single.just(emptyList())
					} else {
						Single.zip(apis) { results ->
							results.filterIsInstance<Goods>()
						}
					}
				}
		}
			.map { list -> list.map { it.copy(title = it.title.plus("Update")) } }
			.flatMap { localRepository.updateAll(it).toFlowable() }.subscribe()
	}
}
