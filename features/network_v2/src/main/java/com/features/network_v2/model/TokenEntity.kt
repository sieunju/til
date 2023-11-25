package com.features.network_v2.model

import android.util.Base64
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Description :
 *
 * Created by juhongmin on 11/24/23
 */
@Serializable
data class TokenEntity(
    val token: String = "",
) {
    fun getDecodeToken(): String {
        val decodedBytes = Base64.decode(token,Base64.DEFAULT)
        return String(decodedBytes,Charsets.UTF_8)
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun getPayload(json: Json): TokenPayload {
        val startIdx = token.indexOf(".")
        val endIdx = token.lastIndexOf(".")
        val bytes = Base64.decode(token.substring(startIdx,endIdx),Base64.DEFAULT)
        return json.decodeFromString(String(bytes,Charsets.UTF_8))
    }

    @Serializable
    data class TokenPayload(
        val type: String = "",
        val nickname: String = "",
        val exp: Long = 0
    ) {
        fun getExpiredMs(): Long {
            return exp * 1000
        }
    }
}
