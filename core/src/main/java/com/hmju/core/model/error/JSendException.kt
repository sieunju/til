package com.hmju.core.model.error

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody

/**
 * Description : JSend Exception
 *
 * Created by juhongmin on 11/22/23
 */
sealed class JSendException(
    open val msg: String? = null,
    open val err: Throwable? = null,
) : RuntimeException(msg, err) {
    data class Network(
        override val msg: String? = null,
        override val err: Throwable? = null,
    ) : JSendException(msg, err)

    /**
     * JSend 규칙에 안맞는 경우 에러 뱉도록 처리
     */
    data class Invalidate(
        override val msg: String? = null,
        override val err: Throwable? = null,
    ) : JSendException(msg, err)

    data class JSendResponse(
        val statusCode: Int,
        val errBody: ResponseBody? = null,
        override val cause: Throwable? = null,
    ) : JSendException(null, cause) {

        val json: Json by lazy {
            Json {
                isLenient = true // Json 큰따옴표 느슨하게 체크.
                ignoreUnknownKeys = true // Field 값이 없는 경우 무시
                coerceInputValues = true // "null" 이 들어간경우 default Argument 값으로 대체
            }
        }

        @OptIn(ExperimentalSerializationApi::class)
        inline fun <reified T> getErrorBody(): T? {
            val body = errBody ?: return null
            return json.decodeFromString(body.string())
        }
    }

    inline fun <reified T> getBody(): T? {
        return if (this is JSendResponse) {
            this.getErrorBody()
        } else {
            null
        }
    }
}
