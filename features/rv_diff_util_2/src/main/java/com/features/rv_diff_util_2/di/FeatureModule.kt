package com.features.rv_diff_util_2.di

import com.features.rv_diff_util_2.ApiService
import com.features.rv_diff_util_2.impl.RvDiffUtil2BridgeImpl
import com.features.rv_diff_util_2_bridge.RvDiffUtil2Bridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 3/10/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {
    @Provides
    fun provideBridge(): RvDiffUtil2Bridge {
        return RvDiffUtil2BridgeImpl()
    }

    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }
}
