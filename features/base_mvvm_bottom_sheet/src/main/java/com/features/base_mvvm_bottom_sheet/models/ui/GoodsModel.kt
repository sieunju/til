package com.features.base_mvvm_bottom_sheet.models.ui

import com.features.base_mvvm_bottom_sheet.models.entity.GoodsEntity

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
