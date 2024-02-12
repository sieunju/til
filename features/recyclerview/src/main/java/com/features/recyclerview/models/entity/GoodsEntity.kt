package com.features.recyclerview.models.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Description :
 *
 * Created by juhongmin on 2/12/24
 */
@Serializable
data class GoodsEntity(
    val id: Long = 0,
    val title: String = "",
    val message: String = "",
    @SerialName("image_path")
    val imagePath: String = ""
)
