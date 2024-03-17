package com.features.async_migrate.di

import android.content.Context
import com.features.async_migrate.ApiService
import com.features.async_migrate.impl.AsyncMigrateBridgeImpl
import com.features.async_migrate_bridge.AsyncMigrateBridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {

    @Provides
    fun provideBridge(
        @ApplicationContext context: Context
    ): AsyncMigrateBridge {
        return AsyncMigrateBridgeImpl(context)
    }

    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }
}