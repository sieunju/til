package com.hmju.core.local.di

import android.content.Context
import androidx.room.Room
import com.hmju.core.local.AppDataBase
import com.hmju.core.local.dao.GoodsDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 11.
 */
@InstallIn(SingletonComponent::class)
@Module
internal object LocalModule {
    @Provides
    @Singleton
    fun provideAppDataBase(
        @ApplicationContext context: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            context, AppDataBase::class.java,
            "til-database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGoodsDao(dataBase: AppDataBase): GoodsDAO = dataBase.goodsDao()
}
