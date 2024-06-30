package com.features.async_migrate.models.ui

import com.features.async_migrate.models.entity.GoodsEntity

/**
 * Description : 상품 데이터 모델
 *
 * Created by juhongmin on 2/14/24
 */
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
