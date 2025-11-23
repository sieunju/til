package com.features.room_observer.di

import com.features.room_observer.repository.ApiService
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {

    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }
}
