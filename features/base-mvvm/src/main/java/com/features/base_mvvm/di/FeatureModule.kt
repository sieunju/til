package com.features.base_mvvm.di

import com.features.base_mvvm.impl.BaseMvvmBridgeImpl
import com.features.base_mvvm_bridge.BaseMvvmBridge
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description : Base MVVM-UI Module
 *
 * Created by juhongmin on 2022/07/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class FeatureModule {
    @Singleton
    @Binds
    abstract fun bindMvvmRequirements(requirements: BaseMvvmBridgeImpl): BaseMvvmBridge
}
