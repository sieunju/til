package com.feature.async_migrate.di

import com.feature.async_migrate.ApiService
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider
            .getNetwork()
            .create(ApiService::class.java)
    }
}