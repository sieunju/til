package com.hmju.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Description : TO-BE Goods API DTO
 *
 * Created by juhongmin on 2025. 8. 14.
 */
@Serializable
data class GoodsDTO(
    val id: Long = 0,
    val title: String = "",
    val message: String = "",
    @SerialName("image_path")
    val imagePath: String = ""
)
