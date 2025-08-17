package com.features.room_observer.models

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

/**
 * Description : RoomObserver 에 대한 파라미터
 *
 * Created by juhongmin on 2025. 8. 15.
 */
data class RoomObserverParams(
	var pageNo: Int = 1,
	private val actionBehavior: Subject<ActionIntent> = BehaviorSubject.createDefault(ActionIntent.INIT)
) {

	fun observerAction(): Flowable<ActionIntent> {
		return actionBehavior.toFlowable(BackpressureStrategy.BUFFER)
	}

	fun sendAction(action: ActionIntent) {
		actionBehavior.onNext(action)
		if (action == ActionIntent.LOAD_MORE) {
			pageNo++
		} else if (action == ActionIntent.INIT ||
			action == ActionIntent.FORCE_REFRESH
		) {
			pageNo = 1
		}
	}

	fun getContentsSize(action: ActionIntent): Int {
		if (action == ActionIntent.INIT) return 3
		if (action == ActionIntent.FORCE_REFRESH) return 3
		if (action == ActionIntent.FOLD) return 3
		// LoadMore
		return 3 + (pageNo * 5)
	}
}