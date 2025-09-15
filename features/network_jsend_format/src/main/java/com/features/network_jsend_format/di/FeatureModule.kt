package com.features.network_jsend_format.di

import com.features.network_jsend_format.ApiService
import com.features.network_jsend_format.impl.NetworkJSendFormatBridgeImpl
import com.features.network_jsend_format_bridge.NetworkJSendFormatBridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
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
    fun provideBridge(): NetworkJSendFormatBridge {
        return NetworkJSendFormatBridgeImpl()
    }
}
