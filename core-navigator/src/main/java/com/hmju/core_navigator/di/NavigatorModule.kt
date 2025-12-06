package com.hmju.core_navigator.di

import com.hmju.core_navigator.Navigator
import com.hmju.core_navigator.impl.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description :
 *
 * Created by juhongmin on 2025. 10. 20.
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class NavigatorModule {
    @Singleton
    @Binds
    abstract fun bindNavigationImpl(
	impl: NavigatorImpl
    ): Navigator
}
