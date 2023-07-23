package com.feature.compose_ui.di

import com.feature.compose_ui.impl.ComposeUiBridgeImpl
import com.feature.compose_ui_bridge.ComposeUiBridge
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description : Compose UI MOdule
 *
 * Created by juhongmin on 2023/07/23
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class FeatureModule {
    @Singleton
    @Binds
    abstract fun bindComposeBridge(impl: ComposeUiBridgeImpl): ComposeUiBridge
}
