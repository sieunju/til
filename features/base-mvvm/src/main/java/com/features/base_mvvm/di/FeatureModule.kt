package com.features.base_mvvm.di

import android.content.Context
import com.features.base_mvvm.impl.BaseMvvmBridgeImpl
import com.features.base_mvvm_bridge.BaseMvvmBridge
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Description : 네트워크 통신 모듈
 *
 * Created by juhongmin on 2023/03/27
 */
@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {

    @Provides
    fun provideBridge(
        @ApplicationContext context: Context
    ): BaseMvvmBridge {
        return BaseMvvmBridgeImpl(context)
    }
}
