package com.features.recyclerview.di

import com.features.recyclerview.ApiService
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description : 네트워크 통신 모듈
 *
 * Created by juhongmin on 2023/03/27
 */
@InstallIn(SingletonComponent::class)
@Module
internal object NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }
}
