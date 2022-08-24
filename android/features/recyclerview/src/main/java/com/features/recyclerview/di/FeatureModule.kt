package com.features.recyclerview.di

import com.features.recyclerview.impl.RecyclerViewRequirementsImpl
import com.features.recyclerview_requirements.RecyclerViewRequirements
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
    abstract fun bindRequirements(requirements: RecyclerViewRequirementsImpl): RecyclerViewRequirements
}
