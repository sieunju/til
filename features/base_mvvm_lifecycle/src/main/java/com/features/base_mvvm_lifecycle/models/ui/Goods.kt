package com.features.base_mvvm_lifecycle.models.ui

import com.features.base_mvvm_lifecycle.models.entity.GoodsDTO

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