package com.features.network_v2.di

import com.features.network_v2.impl.NetworkV2BridgeImpl
import com.features.network_v2_bridge.NetworkV2Bridge
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class FeatureModule {
    @Singleton
    @Binds
    abstract fun bindBridge(requirements: NetworkV2BridgeImpl): NetworkV2Bridge
}
