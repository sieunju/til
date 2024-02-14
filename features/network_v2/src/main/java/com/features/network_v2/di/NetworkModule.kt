package com.features.network_v2.di

import com.features.network_v2.ApiService
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description : 네트워크 v2 모듈
 *
 * Created by juhongmin on 11/23/23
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
