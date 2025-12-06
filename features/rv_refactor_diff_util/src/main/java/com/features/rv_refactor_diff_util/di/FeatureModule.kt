package com.features.rv_refactor_diff_util.di

import com.features.rv_refactor_diff_util.ApiService
import com.features.rv_refactor_diff_util.impl.RvRefactorDiffUtilBridgeImpl
import com.features.rv_refactor_diff_util_bridge.RvRefactorDiffUtilBridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
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

    @Provides
    fun provideBridge(): RvRefactorDiffUtilBridge {
        return RvRefactorDiffUtilBridgeImpl()
    }
}
