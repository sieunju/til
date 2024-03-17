package com.hmju.core.models.body

import kotlinx.serialization.Serializable

@Serializable
data class LikeRequestBody(
    val id: Long
)