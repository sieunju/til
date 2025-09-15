package com.features.network_error_handling.di

import com.features.network_error_handling.ApiService
import com.features.network_error_handling.impl.NetworkErrorHandlingBridgeImpl
import com.features.network_error_handling_bridge.NetworkErrorHandlingBridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by juhongmin on 2/25/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {

    @Singleton
    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideBridge(): NetworkErrorHandlingBridge {
        return NetworkErrorHandlingBridgeImpl()
    }
}
