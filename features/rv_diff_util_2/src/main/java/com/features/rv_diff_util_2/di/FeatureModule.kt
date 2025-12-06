package com.features.rv_diff_util_2.di

import com.features.rv_diff_util_2.ApiService
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 3/10/24
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
