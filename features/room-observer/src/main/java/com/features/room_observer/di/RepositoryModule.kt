package com.features.room_observer.di

import com.features.room_observer.impl.GoodsRepositoryImpl
import com.features.room_observer.impl.LocalRepositoryImpl
import com.features.room_observer.repository.GoodsRepository
import com.features.room_observer.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepositoryModule {
	@Binds
	abstract fun bindLocalRepository(impl: LocalRepositoryImpl): LocalRepository

	@Binds
	abstract fun bindGoodsRepository(impl: GoodsRepositoryImpl): GoodsRepository
}
