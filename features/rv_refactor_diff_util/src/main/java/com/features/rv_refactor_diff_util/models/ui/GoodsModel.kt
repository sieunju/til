package com.features.rv_refactor_diff_util.models.ui

import com.features.rv_refactor_diff_util.models.entity.GoodsEntity

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
