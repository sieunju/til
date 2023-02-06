package com.hmju.core.model.base

import com.hmju.core.model.error.JSendEmptyDataException
import com.hmju.core.model.error.JSendInvalidPayloadException
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * Description : Coroutines Network Base Response
 *
 * Created by juhongmin on 2023/02/04
 */
sealed class ApiResponse<out T> {
    /**
     * Network Success Response Data
     */
    data class Success<out T>(val data: T) : ApiResponse<T>()

    /**
     * Network Fail
     * TODO ErrorBody 로 메시지 처리해야함
     */
    data class Fail(val errBody: ResponseBody?) : ApiResponse<Nothing>()

    /**
     * Exception
     */
    data class Error(val ex: Throwable) : ApiResponse<Nothing>()

    companion object {
        /**
         * Converter HTTP Response -> ApiResponse
         * @param response HTTP 통해 전달 받은 데이터
         */
        fun of(response: Response<*>): ApiResponse<Any> {
            return try {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val body = responseBody.performErrorHandling()
                    Success(body)
                } else {
                    Fail(response.errorBody())
                }
            } catch (ex: Throwable) {
                Error(ex)
            }
        }

        @Throws(JSendInvalidPayloadException::class, JSendEmptyDataException::class)
        private fun Any.performErrorHandling(): Any {
            return if (isJSendFormat()) {
                this
            } else {
                if (this is BaseJSend) {
                    throw JSendInvalidPayloadException(message)
                } else {
                    throw JSendInvalidPayloadException("Invalid Exception")
                }
            }
        }

        /**
         * JSend 규칙에 맞게 JSON 으로 되어있고 data -> payload 값이 존재 하는 경우
         */
        @Throws(JSendEmptyDataException::class)
        private fun Any.isJSendFormat(): Boolean {
            return when (this) {
                is JSendObj<*> -> {
                    if (this.isValid) {
                        true
                    } else if (isSuccess) {
                        throw JSendEmptyDataException(message)
                    } else {
                        false
                    }
                }
                is JSendObjWithMeta<*, *> -> {
                    if (this.isValid) {
                        true
                    } else if (isSuccess) {
                        throw JSendEmptyDataException(message)
                    } else {
                        false
                    }
                }
                is JSendList<*> -> {
                    if (this.isValid) {
                        true
                    } else if (isSuccess) {
                        throw JSendEmptyDataException(message)
                    } else {
                        false
                    }
                }
                is JSendListWithMeta<*, *> -> {
                    if (this.isValid) {
                        true
                    } else if (isSuccess) {
                        throw JSendEmptyDataException(message)
                    } else {
                        false
                    }
                }
                // 규격화된 방식이 아닌경우 true 리턴
                else -> true
            }
        }
    }
}
