package com.features.network.di

import com.features.network.impl.NetworkBridgeImpl
import com.features.network_bridge.NetworkBridge
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description : Network-UI Module
 *
 * Created by juhongmin on 2022/07/22
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class FeatureModule {
    @Singleton
    @Binds
    abstract fun bindNetworkRequirements(requirements: NetworkBridgeImpl): NetworkBridge
}
