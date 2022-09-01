package com.hmju.core.model.goods

import kotlinx.serialization.Serializable

@Serializable
data class GoodsEntity(
    val id: Long = 0,
    val title: String = "",
    val message: String = "",
    val imagePath: String = ""
)
