package com.hmju.network.adapter

import com.hmju.core.model.base.ApiResponse
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
 * @
 * Created by juhongmin on 2023/02/04
 */
class CoroutineErrorHandlingCallAdapter(
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *> {
        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        Timber.d("ResultType $resultType")
        return CallExecuteCoroutineCallAdapter(resultType, coroutineScope)
    }

    private class CallExecuteCoroutineCallAdapter(
        private val responseType: Type,
        private val coroutineScope: CoroutineScope
    ) : CallAdapter<Any, Call<ApiResponse<Any>>> {
        // Request, Response

        override fun responseType() = responseType

        override fun adapt(call: Call<Any>): Call<ApiResponse<Any>> {
            return CallExecuteCoroutinesDelegate(call, coroutineScope)
        }
    }

    private class CallExecuteCoroutinesDelegate(
        private val originCall: Call<Any>,
        private val coroutineScope: CoroutineScope
    ) : Call<ApiResponse<Any>> {
        override fun clone(): Call<ApiResponse<Any>> {
            return CallExecuteCoroutinesDelegate(originCall.clone(), coroutineScope)
        }

        override fun enqueue(callback: Callback<ApiResponse<Any>>) {
            coroutineScope.launch {
                try {
                    val response = originCall.awaitResponse()
                    val body = response.body()
                    if (body != null) {
                        Timber.d("enqueue SUCCESS $body")
                        callback.onResponse(
                            this@CallExecuteCoroutinesDelegate,
                            Response.success(ApiResponse.Success(body))
                        )
                    } else {
                        Timber.d("enqueue FAIL $response")
                        callback.onResponse(
                            this@CallExecuteCoroutinesDelegate,
                            Response.success(ApiResponse.Fail(response))
                        )
                    }

                } catch (ex: Exception) {
                    Timber.d("enqueue ERROR $ex")
                    callback.onResponse(
                        this@CallExecuteCoroutinesDelegate,
                        Response.success(ApiResponse.Error(ex))
                    )
                }
            }
        }

        override fun isExecuted(): Boolean {
            return originCall.isExecuted
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

        override fun execute(): Response<ApiResponse<Any>> {
            return runBlocking(coroutineScope.coroutineContext) {
                try {
                    val response = originCall.execute()
                    val body = response.body()
                    Timber.d("execute ${response.code()}")
                    if (response.isSuccessful && body != null) {
                        Response.success(ApiResponse.Success(body))
                    } else {
                        Response.success(ApiResponse.Fail(response))
                    }
                } catch (ex: Throwable) {
                    Response.success(ApiResponse.Error(ex))
                }
            }
        }

        override fun cancel() {
            originCall.cancel()
        }
    }
}
