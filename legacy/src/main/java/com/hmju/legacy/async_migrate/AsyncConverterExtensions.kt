package com.hmju.legacy.async_migrate

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope


/**
 * Rx Single to Coroutine 으로 변환 하는 함수
 * @param dispatcher Dispatcher 설정 기본값은 IO
 *
 */
suspend inline fun <reified T : Any> Single<T>.toCoroutine(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): T {
    return coroutineScope {
        val work = async(dispatcher) { this@toCoroutine.blockingGet() }
        work.await()
    }
}

/**
 * Rx Flowable to Coroutine 으로 변환 하는 함수
 * @param dispatcher Dispatcher 설정 기본값은 IO
 *
 */
suspend inline fun <reified T : Any> Flowable<T>.toCoroutine(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): T {
    return coroutineScope {
        val work = async(dispatcher) { this@toCoroutine.blockingFirst() }
        work.await()
    }
}
