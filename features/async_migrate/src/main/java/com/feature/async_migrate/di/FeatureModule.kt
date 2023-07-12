package com.feature.async_migrate.di

import com.feature.async_migrate.impl.AsyncMigrateBridgeImpl
import com.feature.async_migrate_bridge.AsyncMigrateBridge
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by juhongmin on 2023/05/01
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class FeatureModule {

    @Singleton
    @Binds
    abstract fun bindAsyncMigrateBridge(impl: AsyncMigrateBridgeImpl): AsyncMigrateBridge
}