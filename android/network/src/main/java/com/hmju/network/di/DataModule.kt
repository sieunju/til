package com.hmju.network.di

import com.hmju.core.repository.*
import com.hmju.network.impl.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description : Data Module Class
 *
 * Created by juhongmin on 2022/01/12
 */
@InstallIn(SingletonComponent::class)
@Module(includes = [RemoteModule::class])
internal abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindGoodsRepository(repository: GoodsRepositoryImpl): GoodsRepository

    @Singleton
    @Binds
    abstract fun bindAuthRepository(repository: AuthRepositoryImpl): AuthRepository

    @Singleton
    @Binds
    abstract fun bindJSendRepository(repository: JSendRepositoryImpl): JSendRepository

    @Singleton
    @Binds
    abstract fun bindErrorHandlingRepository(repository: ErrorHandlingRepositoryImpl): ErrorHandlingRepository

    @Singleton
    @Binds
    abstract fun bindRefreshTokenRepository(repository: RefreshTokenRepositoryImpl): RefreshTokenRepository
}
