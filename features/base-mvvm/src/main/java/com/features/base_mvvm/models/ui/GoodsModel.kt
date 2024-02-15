package com.features.base_mvvm.models.ui

import com.features.base_mvvm.models.entity.GoodsEntity

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
