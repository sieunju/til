package com.hmju.compose_permissions_result.di

import android.content.Context
import com.hmju.compose_permissions_result.impl.ComposePermissionsResultBridgeImpl
import com.hmju.compose_permissions_result_bridge.ComposePermissionsResultBridge
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 4/3/24
 */
@InstallIn(SingletonComponent::class)
@Module
class FeatureModule {
    @Provides
    fun provideBridge(
        @ApplicationContext context: Context
    ): ComposePermissionsResultBridge {
        return ComposePermissionsResultBridgeImpl(context)
    }
}
