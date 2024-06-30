package com.hmju.core.models.auth

import com.hmju.core.models.serializer.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class AuthTokenEntity(
    @SerialName("access_token")
    val token: String = "",
    @SerialName("access_expired_at")
    @Serializable(DateSerializer::class)
    val tokenExpiredAt: Date = Date(),
    @SerialName("refresh_token")
    val refreshToken: String = "",
    @Serializable(DateSerializer::class)
    @SerialName("refresh_expired_at")
    val refreshTokenExpiredAt: Date = Date()
)
