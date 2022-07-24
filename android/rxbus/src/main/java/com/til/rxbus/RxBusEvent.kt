package com.til.rxbus

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

/**
 * Description : RxBusEvent
 *
 * Created by juhongmin on 2022/02/27
 */

object RxBus {
    private val publisher = PublishSubject.create<Any>()

    fun publish(event: Any) {
        publisher.onNext(event)
    }

    fun <T : Any> listen(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)
}

class RxBusEvent {
    data class SimpleLikeEvent(
        val isAdd : Boolean,
        val id: Long
    )
    data class TestEvent(val id : Long)
}