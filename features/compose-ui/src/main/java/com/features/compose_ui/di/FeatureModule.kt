package com.features.compose_ui.di

import android.content.Context
import com.features.compose_ui.ApiService
import com.features.compose_ui.impl.ComposeUiBridgeImpl
import com.features.compose_ui_bridge.ComposeUiBridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 1/20/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {

    @Provides
    fun provideBridge(
        @ApplicationContext context: Context
    ): ComposeUiBridge {
        return ComposeUiBridgeImpl(context)
    }

    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }
}
