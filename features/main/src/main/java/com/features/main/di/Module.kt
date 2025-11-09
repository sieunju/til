package com.features.main.di

import com.features.main.MainRouter
import com.hmju.core_navigator.Router
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by juhongmin on 2025. 11. 9.
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class Module {

    @Singleton
    @Binds
    @IntoSet
    abstract fun bindRouterImpl(impl: MainRouter): Router
}
