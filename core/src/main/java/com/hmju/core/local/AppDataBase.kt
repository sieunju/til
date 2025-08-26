package com.hmju.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hmju.core.local.converter.DateTimeConverters
import com.hmju.core.local.dao.GoodsDAO
import com.hmju.core.local.models.GoodsEntity

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 11.
 */
@Database(entities = [GoodsEntity::class], version = 1)
@TypeConverters(DateTimeConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun goodsDao(): GoodsDAO
}
