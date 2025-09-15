package com.features.base_mvvm_bottom_sheet.di

import com.features.base_mvvm_bottom_sheet.impl.BaseMvvmBottomSheetBridgeImpl
import com.features.base_mvvm_bottom_sheet.source.ApiService
import com.features.base_mvvm_bottom_sheet_bridge.BaseMvvmBottomSheetBridge
import com.hmju.core.network.NetworkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 3/14/24
 */
@InstallIn(SingletonComponent::class)
@Module
internal object FeatureModule {
    @Provides
    fun provideBridge(): BaseMvvmBottomSheetBridge {
        return BaseMvvmBottomSheetBridgeImpl()
    }

    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): ApiService {
        return provider.createApiService(ApiService::class.java)
    }
}
