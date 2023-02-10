package com.hmju.network.adapter

import com.hmju.core.model.base.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Request
import okio.Timeout
import retrofit2.*
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Description : Coroutines Call Adapter
 * References RxJava3CallAdapterFactory
 * Created by juhongmin on 2023/02/04
 */
class CoroutineErrorHandlingCallAdapter(
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : CallAdapter.Factory() {

    companion object {
        fun create(): CallAdapter.Factory {
            return CoroutineErrorHandlingCallAdapter()
        }
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        return when (getRawType(returnType)) {
            Call::class.java -> {
                // ApiResponse<JSend...> -> Original (Call<ApiResponse<JSend...>)
                val callType = getParameterUpperBound(0, returnType as ParameterizedType)

                // ApiResponse 으로 감싸져 있는지 확인
                if (getRawType(callType) == ApiResponse::class.java) {
                    // ApiResponse Find {<JSend...>}
                    val jsendType = getParameterUpperBound(0, callType as ParameterizedType)
                    Timber.d("ApiResponse Format $jsendType")
                    CoroutineCallAdapterWrapper(jsendType, coroutineScope)
                } else {
                    null
                }
            }
            else -> null
        }
    }

    inner class CoroutineCallAdapterWrapper<R : Type>(
        private val responseType: R,
        private val coroutineScope: CoroutineScope
    ) : CallAdapter<R, Any> {
        // CallAdapter<Request,Response>

        /**
         * @see JSendObj
         * @see JSendObjWithMeta
         * @see JSendList
         * @see JSendListWithMeta
         * 이외는 isValid 체크 X
         */
        override fun responseType(): Type {
            return responseType
        }

        override fun adapt(call: Call<R>): Any {
            return CallEnqueueDelegate(call, coroutineScope)
        }
    }

    inner class CallEnqueueDelegate<T>(
        private val originCall: Call<T>,
        private val coroutineScope: CoroutineScope
    ) : Call<Any> {
        override fun clone(): Call<Any> {
            return CallEnqueueDelegate(originCall.clone(), coroutineScope)
        }

        override fun execute(): Response<Any> {
            return runBlocking(coroutineScope.coroutineContext) {
                val apiResponse = ApiResponse.of(originCall.execute())
                Timber.d("Execute ApiResponse $apiResponse")
                Response.success(apiResponse)
            }
        }

        override fun enqueue(callback: Callback<Any>) {
            coroutineScope.launch {
                val apiResponse = ApiResponse.of(originCall.awaitResponse())
                Timber.d("Enqueue ApiResponse $apiResponse")
                callback.onResponse(this@CallEnqueueDelegate, Response.success(apiResponse))
            }
        }

        override fun isExecuted(): Boolean {
            return originCall.isExecuted
        }

        override fun cancel() {
            return originCall.cancel()
        }

        override fun isCanceled(): Boolean {
            return originCall.isCanceled
        }

        override fun request(): Request {
            return originCall.request()
        }

        override fun timeout(): Timeout {
            return originCall.timeout()
        }
    }

//    private class CallExecuteCoroutinesDelegate(
//        private val originCall: Call<Any>,
//        private val coroutineScope: CoroutineScope
//    ) : Call<ApiResponse<Any>> {
//        override fun clone(): Call<ApiResponse<Any>> {
//            return CallExecuteCoroutinesDelegate(originCall.clone(), coroutineScope)
//        }
//
//        override fun enqueue(callback: Callback<ApiResponse<Any>>) {
//            coroutineScope.launch {
//                try {
//                    val response = originCall.awaitResponse()
//                    val body = response.body()
//                    if (body != null) {
//                        Timber.d("enqueue SUCCESS $body")
//                        callback.onResponse(
//                            this@CallExecuteCoroutinesDelegate,
//                            Response.success(ApiResponse.Success(body))
//                        )
//                    } else {
//                        Timber.d("enqueue FAIL $response")
//                        callback.onResponse(
//                            this@CallExecuteCoroutinesDelegate,
//                            Response.success(ApiResponse.Fail(response))
//                        )
//                    }
//
//                } catch (ex: Exception) {
//                    Timber.d("enqueue ERROR $ex")
//                    callback.onResponse(
//                        this@CallExecuteCoroutinesDelegate,
//                        Response.success(ApiResponse.Error(ex))
//                    )
//                }
//            }
//        }
//
//        override fun isExecuted(): Boolean {
//            return originCall.isExecuted
//        }
//
//        override fun isCanceled(): Boolean {
//            return originCall.isCanceled
//        }
//
//        override fun request(): Request {
//            return originCall.request()
//        }
//
//        override fun timeout(): Timeout {
//            return originCall.timeout()
//        }
//
//        override fun execute(): Response<ApiResponse<Any>> {
//            return runBlocking(coroutineScope.coroutineContext) {
//                try {
//                    val response = originCall.execute()
//                    val body = response.body()
//                    Timber.d("execute ${response.code()}")
//                    if (response.isSuccessful && body != null) {
//                        Response.success(ApiResponse.Success(body))
//                    } else {
//                        Response.success(ApiResponse.Fail(response))
//                    }
//                } catch (ex: Throwable) {
//                    Response.success(ApiResponse.Error(ex))
//                }
//            }
//        }
//
//        override fun cancel() {
//            originCall.cancel()
//        }
//    }
}