package com.features.rv_custom_paging.di

import com.features.rv_custom_paging.ApiService
import com.features.rv_custom_paging.impl.RecyclerViewCustomPagingBridgeImpl
import com.features.rv_custom_paging_bridge.RecyclerViewCustomPagingBridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by juhongmin on 2/15/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {

    @Singleton
    @Provides
    fun provideBridge(): RecyclerViewCustomPagingBridge {
        return RecyclerViewCustomPagingBridgeImpl()
    }

    @Singleton
    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }
}
