package com.features.main

import com.features.async_migrate_bridge.AsyncMigrateBridge
import com.features.base_mvvm_bridge.BaseMvvmBridge
import com.features.compose_navigation_bridge.ComposeNavigationBridge
import com.features.compose_ui_bridge.ComposeUiBridge
import com.features.network_bridge.NetworkBridge
import com.features.recyclerview_bridge.RecyclerViewBridge
import com.features.room_observer_bridge.RoomObserverBridge
import com.hmju.compose_permissions_result_bridge.ComposePermissionsResultBridge
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
    private val networkBridge: NetworkBridge,
    private val recyclerViewBridge: RecyclerViewBridge,
    private val mvvmRequirements: BaseMvvmBridge,
    private val asyncMigrateBridge: AsyncMigrateBridge,
    private val composeUiBridge: ComposeUiBridge,
    private val composePermissionsResultBridge: ComposePermissionsResultBridge,
    private val composeNavigationBridge: ComposeNavigationBridge,
    private val roomObserverBridge: RoomObserverBridge
) : ActivityViewModel() {

    fun moveToNetworkPage() {
        networkBridge.moveToPage()
    }

    fun moveToRecyclerViewPage() {
        recyclerViewBridge.moveToPage()
    }

    fun moveToMvvmLifecyclePage() {
        mvvmRequirements.moveToPage()
    }

    fun moveToAsyncMigratePage() {
        asyncMigrateBridge.moveToPage()
    }

    fun moveToComposeUiPage() {
        composeUiBridge.moveToPage()
    }

    fun moveToMemoComposePage() {
        composeUiBridge.moveToMemoPage()
    }

    fun moveToPermissionsResultPage() {
        composePermissionsResultBridge.moveToPage()
    }

    fun moveToComposeNavigationPage() {
        composeNavigationBridge.moveToPage()
    }

    fun moveToRoomObserverPage(){
        roomObserverBridge.moveToPage()
    }
}
