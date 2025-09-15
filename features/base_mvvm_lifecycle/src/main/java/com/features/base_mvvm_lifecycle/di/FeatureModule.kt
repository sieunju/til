package com.features.base_mvvm_lifecycle.di

import android.content.Context
import com.features.base_mvvm_lifecycle.impl.BaseMvvmLifecycleBridgeImpl
import com.features.base_mvvm_lifecycle.source.ApiService
import com.features.base_mvvm_lifecycle_bridge.BaseMvvmLifecycleBridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 3/14/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {
    @Provides
    fun provideBridge(
        @ApplicationContext context: Context
    ): BaseMvvmLifecycleBridge {
        return BaseMvvmLifecycleBridgeImpl(context)
    }

    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }
}
