package com.hmju.core.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hmju.core.local.models.GoodsEntity.Companion.TABLE_NAME
import kotlinx.datetime.LocalDateTime

/**
 * Description : Backup GoodsEntity
 *
 * Created by juhongmin on 2025. 8. 11.
 */
@Entity(tableName = TABLE_NAME)
data class GoodsEntity(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = Column.ID)
	var id: Long = 0L,
	@ColumnInfo(name = Column.GOODS_ID)
	val goodsId: Long,
	@ColumnInfo(name = Column.USER_ID)
	val userId: String,
	@ColumnInfo(name = Column.TITLE)
	val title: String,
	@ColumnInfo(name = Column.MSG)
	val message: String,
	@ColumnInfo(name = Column.IMG_PATH)
	val imagePath: String,
	@ColumnInfo(name = Column.CREATED_AT)
	val createdAt: LocalDateTime
) {

	companion object {
		const val TABLE_NAME = "GOODS"

		object Column {
			const val ID = "ID"
			const val GOODS_ID = "GOODS_ID"
			const val USER_ID = "USER_ID"
			const val TITLE = "TITLE"
			const val MSG = "MSG"
			const val IMG_PATH = "IMG_PATH"
			const val CREATED_AT = "CREATED_AT"
		}
	}
}