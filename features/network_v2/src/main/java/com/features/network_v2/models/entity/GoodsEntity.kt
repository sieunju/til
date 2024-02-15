package com.features.network_v2.models.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoodsEntity(
    val id: Long = 0,
    val title: String = "",
    val message: String = "",
    @SerialName("image_path")
    val imagePath: String = ""
)
