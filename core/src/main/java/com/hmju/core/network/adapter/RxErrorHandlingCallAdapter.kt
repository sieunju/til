package com.hmju.core.network.adapter

import com.hmju.core.models.base.*
import com.hmju.core.models.error.JSendException
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Description : RxError Handling Adapter
 *
 * Created by juhongmin on 2022/06/18
 */
class RxErrorHandlingCallAdapter : CallAdapter.Factory() {
    // private val original = RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io())
    private val original = RxJava3CallAdapterFactory.create()

    companion object {
        fun create(): CallAdapter.Factory {
            return RxErrorHandlingCallAdapter()
        }
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val adapter = original.get(returnType, annotations, retrofit)
        return if (adapter != null) {
            RxJavaCallAdapterWrapper(adapter)
        } else {
            null
        }
    }

    inner class RxJavaCallAdapterWrapper<R>(
        private val original: CallAdapter<R, *>
    ) : CallAdapter<R, Any> {

        override fun responseType(): Type = original.responseType()

        override fun adapt(call: Call<R>): Any {
            return when (val res = original.adapt(call)) {
                is Single<*> -> {
                    res.map { validateJSendCheck(it) }
                        .onErrorResumeNext { Single.error(getJSendException(it)) }
                        .subscribeOn(Schedulers.io())
                }

                else -> {
                    throw IllegalArgumentException("Not Invalid Type")
                }
            }
        }

        @Throws(JSendException.Invalidate::class)
        private fun validateJSendCheck(res: Any): Any {
            return if (checkPayload(res)) {
                res
            } else if (res is BaseJSend) {
                throw JSendException.Invalidate(res.message)
            } else {
                throw JSendException.Invalidate("Invalid Exception")
            }
        }

        /**
         * data.payload 데이터 유효한지 체크하는 함수
         */
        @Throws(JSendException.Invalidate::class)
        private fun checkPayload(res: Any): Boolean {
            return when (res) {
                is BaseJSend -> {
                    if (res.isValid) {
                        true
                    } else if (res.isSuccess) {
                        throw JSendException.Invalidate(res.message)
                    } else {
                        false
                    }
                }

                else -> true
            }
        }

        private fun getJSendException(err: Throwable): JSendException {
            return if (err is HttpException) {
                val res = err.response()
                if (res != null) {
                    JSendException.JSendResponse(err.code(), res.errorBody(), err)
                } else {
                    JSendException.Network(err.message, err)
                }
            } else if (err is SocketTimeoutException) {
                JSendException.Network(err.message, err)
            } else if (err is UnknownHostException) {
                JSendException.Network(err.message, err)
            } else if (err is IOException) {
                JSendException.Network(err.message, err)
            } else {
                JSendException.Network(err.message, err)
            }
        }
    }
}
