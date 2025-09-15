package com.features.rv_diff_util_performance.di

import com.features.rv_diff_util_performance.impl.RvDiffUtilPerformanceBridgeImpl
import com.features.rv_diff_util_performance_bridge.RvDiffUtilPerformanceBridge
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
    fun provideBridge(): RvDiffUtilPerformanceBridge {
        return RvDiffUtilPerformanceBridgeImpl()
    }
}
