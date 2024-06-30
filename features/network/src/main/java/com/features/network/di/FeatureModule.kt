package com.features.network.di

import android.content.Context
import com.features.network.impl.NetworkBridgeImpl
import com.features.network_bridge.NetworkBridge
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Description : Network-UI Module
 *
 * Created by juhongmin on 2022/07/22
 */
@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {
    @Provides
    fun provideBridge(
        @ApplicationContext context: Context
    ): NetworkBridge {
        return NetworkBridgeImpl(context)
    }
}
