package com.features.rv_refactor_diff_util.models.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
 */
@Serializable
data class GoodsDTO(
    val id: Long = 0,
    val title: String = "",
    val message: String = "",
    @SerialName("image_path")
    val imagePath: String = ""
)
