package com.hmju.core.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: GoodsEntity): Single<Long>

    @Query("DELETE FROM ${GoodsEntity.TABLE_NAME} WHERE ${GoodsEntity.Companion.Column.ID} = :id")
    abstract fun deleteById(id: Long): Single<Int>

    @Query("SELECT * FROM ${GoodsEntity.TABLE_NAME} WHERE ${GoodsEntity.Companion.Column.USER_ID} = :userId")
    abstract fun findAllByUserId(userId: String): Flowable<List<GoodsEntity>>
}