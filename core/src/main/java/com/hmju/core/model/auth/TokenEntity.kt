package com.hmju.core.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class TokenEntity(
    val token: String = ""
)
