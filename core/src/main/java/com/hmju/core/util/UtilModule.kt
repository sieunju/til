package com.hmju.core.util

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class UtilModule {
    @Singleton
    @Binds
    abstract fun bindResourceManager(
        impl: ResourceManagerImpl
    ): ResourceManager
}
