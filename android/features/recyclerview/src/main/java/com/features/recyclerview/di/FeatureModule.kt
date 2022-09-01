package com.features.recyclerview.di

import com.features.recyclerview.impl.RecyclerViewBridgeImpl
import com.features.recyclerview_bridge.RecyclerViewBridge
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description : RecyclerView-UI Module
 *
 * Created by juhongmin on 2022/07/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class FeatureModule {
    @Singleton
    @Binds
    abstract fun bindRequirements(requirements: RecyclerViewBridgeImpl): RecyclerViewBridge
}
