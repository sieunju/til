package com.features.rv_refactor_diff_util.models.ui

import com.features.rv_refactor_diff_util.models.entity.GoodsDTO

data class Goods(
    val id: Long,
    val title: String,
    val message: String,
    val imagePath: String
) {
    constructor(entity: GoodsDTO) : this(
        id = entity.id,
        title = entity.title,
        message = entity.message,
        imagePath = entity.imagePath
    )
}
