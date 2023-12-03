package com.hmju.core.pref.di

import com.hmju.core.pref.PreferenceManager
import com.hmju.core.pref.PreferenceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class PreferenceManagerModule {
    @Singleton
    @Binds
    abstract fun bindPreferenceManager(impl: PreferenceManagerImpl): PreferenceManager
}
