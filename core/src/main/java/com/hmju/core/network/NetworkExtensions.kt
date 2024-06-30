package com.hmju.core.network

import com.hmju.core.models.base.ApiResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

/**
 * Description : Network Exception
 *
 * Created by juhongmin on 5/6/24
 */
object NetworkExtensions {

    /**
     * Network -> Simple Mapper
     * @param predicate ApiResponse -> Mapping Function
     * @param defValue 네트워크 에러 발생시 기본값 처리
     */
    inline fun <reified I, reified O> ApiResponse<I>.toMap(
        crossinline predicate: (I) -> O,
        defValue: I? = null
    ): Result<O> {
        return when (this) {
            is ApiResponse.Success -> {
                Result.success(predicate(this.data))
            }

            is ApiResponse.Fail -> {
                if (defValue == null) {
                    Result.failure(this.err)
                } else {
                    Result.success(predicate(defValue))
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend inline fun <A : Any, B : Any, R : Any> zip(
        crossinline a: suspend () -> ApiResponse<A>,
        crossinline b: suspend () -> ApiResponse<B>,
        crossinline predicate: (A, B) -> R
    ): Result<R> {
        return coroutineScope {
            val job1 = GlobalScope.async { a() }
            val job2 = GlobalScope.async { b() }
            val res1 = job1.await()
            val res2 = job2.await()
            if (res1 is ApiResponse.Success &&
                res2 is ApiResponse.Success
            ) {
                try {
                    Result.success(predicate(res1.data, res2.data))
                } catch (ex: Exception) {
                    Result.failure(ex)
                }
            } else if (res1 is ApiResponse.Fail) {
                Result.failure(res1.err)
            } else if (res2 is ApiResponse.Fail) {
                Result.failure(res2.err)
            } else {
                Result.failure(IllegalStateException("Not Found Data Instance"))
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend inline fun <A : Any, B : Any, C : Any, R : Any> zip(
        crossinline a: suspend () -> ApiResponse<A>,
        crossinline b: suspend () -> ApiResponse<B>,
        crossinline c: suspend () -> ApiResponse<C>,
        crossinline predicate: (A, B, C) -> R
    ): Result<R> {
        return coroutineScope {
            val job1 = GlobalScope.async { a() }
            val job2 = GlobalScope.async { b() }
            val job3 = GlobalScope.async { c() }
            val res1 = job1.await()
            val res2 = job2.await()
            val res3 = job3.await()
            if (res1 is ApiResponse.Success &&
                res2 is ApiResponse.Success &&
                res3 is ApiResponse.Success
            ) {
                try {
                    Result.success(predicate(res1.data, res2.data, res3.data))
                } catch (ex: Exception) {
                    Result.failure(ex)
                }
            } else if (res1 is ApiResponse.Fail) {
                Result.failure(res1.err)
            } else if (res2 is ApiResponse.Fail) {
                Result.failure(res2.err)
            } else if (res3 is ApiResponse.Fail) {
                Result.failure(res3.err)
            } else {
                Result.failure(IllegalStateException("Not Found Data Instance"))
            }
        }
    }
}