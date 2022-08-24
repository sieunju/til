package com.hmju.core.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class TokenEntity(
    val token: String = ""
)
