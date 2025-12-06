package com.features.compose_navigation.di

import com.features.compose_navigation.ApiService
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 4/6/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {
    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }
}
