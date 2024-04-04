package com.hmju.core.permission.di

import com.hmju.core.permission.PermissionProvider
import com.hmju.core.permission.impl.PermissionProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 4/5/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal abstract class PermissionModule {
    @Binds
    abstract fun bindPermissionProvider(
        impl: PermissionProviderImpl
    ): PermissionProvider
}
