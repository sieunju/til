package com.features.network_v2.models.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JSendEntity(
    @SerialName("id")
    val id: String = ""
)
