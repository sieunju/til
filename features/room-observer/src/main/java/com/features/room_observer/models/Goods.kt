package com.features.room_observer.models

import com.hmju.core.local.models.GoodsEntity

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
data class Goods(
	val userId: String,
	val id: Long = 0,
	val title: String,
	val message: String,
	val imagePath: String
) {

	constructor(userId: String, dto: GoodsDTO) : this(
		userId = userId,
		id = dto.id,
		title = dto.title,
		message = dto.message,
		imagePath = dto.imagePath
	)

	constructor(entity: GoodsEntity) : this(
		userId = entity.userId,
		id = entity.goodsId,
		title = entity.title,
		message = entity.message,
		imagePath = entity.imagePath
	)
}
