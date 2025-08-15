package com.hmju.core.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.hmju.core.local.models.GoodsEntity
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 11.
 */
@Dao
abstract class GoodsDAO {

	@Query("DELETE FROM ${GoodsEntity.TABLE_NAME} WHERE ${GoodsEntity.Companion.Column.ID} = :id")
	abstract fun deleteById(id: Long): Single<Int>

	@Query("SELECT * FROM ${GoodsEntity.TABLE_NAME} WHERE ${GoodsEntity.Companion.Column.USER_ID} = :userId")
	abstract fun findAllByUserIdFlowable(userId: String): Flowable<List<GoodsEntity>>

	@Query(
		"SELECT * FROM ${GoodsEntity.TABLE_NAME} WHERE " +
				"${GoodsEntity.Companion.Column.GOODS_ID} = :goodsId AND " +
				"${GoodsEntity.Companion.Column.USER_ID} = :userId"
	)
	abstract fun findByGoodsIdWithUserId(goodsId: Long, userId: String): Single<GoodsEntity>

	@Query("DELETE FROM ${GoodsEntity.TABLE_NAME}")
	abstract fun clearTable(): Single<Int>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	abstract fun insertAll(list: List<GoodsEntity>): Single<List<Long>>

	@Update
	abstract fun update(entity: GoodsEntity): Single<Int>

	@Transaction
	open fun update(goodsId: Long, entity: GoodsEntity): Int {
		return try {
			val findEntity = findByGoodsIdWithUserId(goodsId, entity.userId)
				.blockingGet()
			val updateEntity = entity.copy(id = findEntity.id)
			update(updateEntity).blockingGet()
		} catch (ex: Exception) {
			throw ex
		}
	}

	@Transaction
	open fun replaceAll(list: List<GoodsEntity>): List<Long> {
		return try {
			clearTable().blockingSubscribe()
			insertAll(list).blockingGet()
		} catch (ex: Exception) {
			throw ex
		}
	}
}