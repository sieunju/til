package com.hmju.core.data.di

import com.hmju.core.data.impl.AuthRepositoryImpl
import com.hmju.core.data.impl.ErrorHandlingRepositoryImpl
import com.hmju.core.data.impl.GoodsRepositoryImpl
import com.hmju.core.data.impl.JSendRepositoryImpl
import com.hmju.core.data.repository.AuthRepository
import com.hmju.core.data.repository.ErrorHandlingRepository
import com.hmju.core.data.repository.GoodsRepository
import com.hmju.core.data.repository.JSendRepository
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
}
