package com.hmju.core.rxbus

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.processors.BehaviorProcessor
import io.reactivex.rxjava3.processors.PublishProcessor

/**
 * Description : RxBus Event
 *
 * Created by juhongmin on 2022/08/23
 */
object RxBus {
    // 구독 이후 값 처리하기
    private val publisher = PublishProcessor.create<RxBusEvent>()

    // 구독 이전 이후, 최근 값 관리
    private val behavior = BehaviorProcessor.create<RxBusEvent>()

    /**
     * 이벤트 데이터 처리
     */
    fun publish(event: RxBusEvent) {
        publisher.onNext(event)
        behavior.onNext(event)
    }

    /**
     * 구독 이전, 이후 값을 가지고 처리하고 싶다면
     */
    fun <T : RxBusEvent> listen(type: Class<T>): Flowable<T> =
        publisher.ofType(type).onBackpressureBuffer()

    /**
     * 구독 이후 값을 처리하고 싶다면
     */
    fun <T : RxBusEvent> behavior(type: Class<T>): Flowable<T> =
        behavior.ofType(type).onBackpressureBuffer()
}

open class RxBusEvent