package com.features.rv_simple_like.di

import com.features.rv_simple_like.ApiService
import com.features.rv_simple_like.impl.RvSimpleLikeBridgeImpl
import com.features.rv_simple_like_bridge.RvSimpleLikeBridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
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

    @Provides
    fun provideBridge(): RvSimpleLikeBridge {
        return RvSimpleLikeBridgeImpl()
    }
}
