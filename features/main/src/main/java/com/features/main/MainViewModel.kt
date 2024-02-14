package com.features.main

import com.features.async_migrate_bridge.AsyncMigrateBridge
import com.features.compose_ui_bridge.ComposeUiBridge
import com.features.base_mvvm_bridge.BaseMvvmBridge
import com.features.network_bridge.NetworkBridge
import com.features.recyclerview_bridge.RecyclerViewBridge
import com.hmju.core.ui.base.ActivityViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/26
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkRequirements: NetworkBridge,
    private val recyclerViewBridge: RecyclerViewBridge,
    private val mvvmRequirements: BaseMvvmBridge,
    private val asyncMigrateBridge: AsyncMigrateBridge,
    private val composeUiBridge: ComposeUiBridge
) : ActivityViewModel() {

    fun moveToNetworkPage() {
        networkRequirements.moveToNetworkPage()
    }

    fun moveToRecyclerViewPage() {
        recyclerViewBridge.moveToRecyclerViewPage()
    }

    fun moveToMvvmLifecyclePage() {
        mvvmRequirements.moveToBaseMvvm()
    }

    fun moveToAsyncMigratePage() {
        asyncMigrateBridge.moveToAsyncMigrate()
    }

    fun moveToComposeUiPage() {
        composeUiBridge.moveToPage()
    }

    fun moveToMemoComposePage() {
        composeUiBridge.moveToMemoPage()
    }
}
