package com.hmju.core.model.body

import kotlinx.serialization.Serializable

@Serializable
data class LikeRequestBody(
    val id: Long
)