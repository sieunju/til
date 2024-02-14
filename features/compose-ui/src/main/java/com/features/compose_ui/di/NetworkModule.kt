package com.features.compose_ui.di

import com.features.compose_ui.ApiService
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by juhongmin on 1/20/24
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
