package com.features.room_observer.usecase

import com.features.room_observer.ApiService
import com.features.room_observer.models.AccountBusEvent
import com.features.room_observer.models.ActionIntent
import com.features.room_observer.models.Goods
import com.features.room_observer.models.RoomObserverParams
import com.features.room_observer.models.State
import com.hmju.core.local.dao.GoodsDAO
import com.hmju.core.pref.PreferenceManager
import com.hmju.core.rxbus.RxBus
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
class RoomObserverUseCase @Inject constructor(
	private val dao: GoodsDAO,
	private val apis: ApiService,
	private val prefProvider: PreferenceManager
) {

	sealed interface WorkState {
		data class DbObserverState(
			val list: List<Goods>,
			val isContentsA: Boolean,
			val action: ActionIntent
		) : WorkState

		data object Skip : WorkState

		data class RemoteState(
			val list: List<Goods>,
			val maxCount: Int,
			val isContentsA: Boolean,
			val action: ActionIntent
		) : WorkState
	}

	companion object {
		private const val KEY_CONTENTS = "ROOM_OBSERVER_CONTENTS"
	}

	operator fun invoke(
		params: RoomObserverParams
	): Flowable<State> {
		return RxBus.listen(AccountBusEvent::class.java)
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
		return params.observerAction().flatMap {
			return@flatMap Flowable.mergeDelayError(localTask(userId, it), remoteTask(userId, it))
		}.map { state ->
			if (state is WorkState.RemoteState || state is WorkState.Skip) return@map State.Skip
			if (state !is WorkState.DbObserverState) throw IllegalArgumentException("Error")
			converterState(params, userId, state)
		}
	}

	private fun converterState(
		params: RoomObserverParams,
		userId: String,
		state: WorkState.DbObserverState
	): State {
		if (state.action == ActionIntent.INIT && state.list.isEmpty()) return State.Skip
		if (state.list.isEmpty()) return State.Empty
		if (state.isContentsA) {
			val takeList = state.list
				.take(params.getContentsSize(state.action))
			return State.ContentsA(
				list = takeList,
				hasMore = takeList.size < state.list.size
			)
		} else {
			return State.ContentsB(
				list = state.list.take(3)
			)
		}
	}

	private fun handleLogout(): Flowable<State> {
		return Flowable.just(State.Skip)
	}

	private fun localTask(userId: String, action: ActionIntent): Flowable<WorkState> {
		return Flowable.zip(
			findAllByUserId(userId),
			isContentsA()
		) { list, isContentsA ->
			return@zip WorkState.DbObserverState(list, isContentsA, action)
		}
	}

	private fun remoteTask(userId: String, action: ActionIntent): Flowable<WorkState> {
		return if (action == ActionIntent.INIT || action == ActionIntent.FORCE_REFRESH) {
			Single.zip(
				fetchGoods(userId),
				fetchMaxCount(),
				fetchIsContentsA()
			) { list, maxCount, isContentsA ->
				return@zip WorkState.RemoteState(list, maxCount, isContentsA, action)
			}.cast(WorkState::class.java).toFlowable()
		} else {
			Flowable.just(WorkState.Skip)
		}
	}


	private fun findAllByUserId(userId: String): Flowable<List<Goods>> {
		return dao.findAllByUserId(userId)
			.map { list -> list.map { Goods(it) } }
	}

	private fun isContentsA(): Flowable<Boolean> {
		return Single.create<Boolean> {
			val value = prefProvider.getString(KEY_CONTENTS)
			if (value == "Y") {
				it.onSuccess(true)
			} else {
				it.onSuccess(false)
			}
		}.toFlowable()
	}

	private fun fetchMaxCount(): Single<Int> {
		return Single.just(5).subscribeOn(Schedulers.io())
	}

	private fun fetchGoods(pageNo: Int, userId: String): Single<List<Goods>> {
		return apis.fetchGoods(pageNo)
			.delay(2000, TimeUnit.MILLISECONDS)
			.map { res -> res.list.map { Goods(userId, it) } }
			.subscribeOn(Schedulers.io())
	}

	private fun fetchGoods(userId: String): Single<List<Goods>> {
		return Single.zip(
			fetchGoods(1, userId),
			fetchGoods(2, userId)
		) { goods1, goods2 ->
			goods1 + goods2
		}
	}

	private fun fetchGoodsById(userId: String, id: Long): Single<Goods> {
		return apis.fetchById(id).map { Goods(userId, it.obj) }
	}

	private fun fetchIsContentsA(): Single<Boolean> {
		return Single.just(Random.nextBoolean())
	}
}
