package com.features.compose_navigation.di

import android.content.Context
import com.features.compose_navigation.ApiService
import com.features.compose_navigation.impl.ComposeNavigationBridgeImpl
import com.features.compose_navigation_bridge.ComposeNavigationBridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideBridge(
        @ApplicationContext context: Context
    ): ComposeNavigationBridge {
        return ComposeNavigationBridgeImpl(context)
    }

    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }
}
