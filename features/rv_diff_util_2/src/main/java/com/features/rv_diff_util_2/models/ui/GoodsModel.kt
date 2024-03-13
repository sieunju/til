package com.features.rv_diff_util_2.models.ui

import com.features.rv_diff_util_2.models.entity.GoodsEntity

data class GoodsModel(
    val id: Long,
    val title: String,
    val message: String,
    val imagePath: String
) {
    constructor(entity: GoodsEntity) : this(
        id = entity.id,
        title = entity.title,
        message = entity.message,
        imagePath = entity.imagePath
    )
}