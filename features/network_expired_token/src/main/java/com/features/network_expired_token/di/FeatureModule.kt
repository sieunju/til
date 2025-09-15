package com.features.network_expired_token.di

import com.features.network_expired_token.ApiService
import com.features.network_expired_token.impl.NetworkExpiredTokenBridgeImpl
import com.features.network_expired_token_bridge.NetworkExpiredTokenBridge
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
    fun provideBridge(): NetworkExpiredTokenBridge {
        return NetworkExpiredTokenBridgeImpl()
    }
}
