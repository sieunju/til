package com.features.main

import com.feature.async_migrate_bridge.AsyncMigrateBridge
import com.feature.compose_ui_bridge.ComposeUiBridge
import com.features.base_mvvm_bridge.BaseMvvmBridge
import com.features.network_bridge.NetworkBridge
import com.features.recyclerview_bridge.RecyclerViewBridge
import com.hmju.core.login_manager.LoginManager
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.lifecycle.OnIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
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
    private val loginManager: LoginManager,
    private val asyncMigrateBridge: AsyncMigrateBridge,
    private val composeUiBridge: ComposeUiBridge
) : ActivityViewModel() {

    @OnIntent
    fun intentData() {
        Timber.d("[s] onCreate Intent Data ===============================================")
        savedStateHandle.keys().forEach {
            Timber.d("Key $it Value ${savedStateHandle.get<Any>(it)}")
        }
        Timber.d("[s] onCreate Intent Data ===============================================")
    }

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

    fun moveToComposeUiPage(){
        composeUiBridge.moveToPage()
    }
}
