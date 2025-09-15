package com.features.network_v2.di

import com.features.network_v2.ApiService
import com.features.network_v2.impl.NetworkV2BridgeImpl
import com.features.network_v2_bridge.NetworkV2Bridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
    fun bindBridge(): NetworkV2Bridge {
        return NetworkV2BridgeImpl()
    }
}
