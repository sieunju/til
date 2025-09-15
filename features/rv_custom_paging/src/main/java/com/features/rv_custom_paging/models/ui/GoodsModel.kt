package com.features.rv_custom_paging.models.ui

import com.features.rv_custom_paging.models.entity.GoodsEntity

data class GoodsModel(
    val id: Long = 0,
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
