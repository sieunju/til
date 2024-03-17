package com.features.rv_refactor_diff_util.models.ui

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
 */
data class GoodsOneModel(
    val id: Long,
    val title: String,
    val message: String,
    val imagePath: String
) {
    constructor(model: GoodsModel) : this(
        id = model.id,
        title = model.title,
        message = model.message,
        imagePath = model.imagePath
    )
}
