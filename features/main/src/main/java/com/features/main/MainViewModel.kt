package com.features.main

import android.net.Uri
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.IntentKey
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.RouteParamsKey
import com.hmju.core_navigator.addQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/26
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ActivityViewModel() {
    fun moveToNetworkPage() {
	sendNavigate(Route.NETWORK.getUri {
	    addQuery(RouteParamsKey.TARGET, "root")
	})
    }

    fun moveToRecyclerViewPage() {
	sendNavigate(Route.RECYCLERVIEW)
    }

    fun moveToMvvmLifecyclePage() {
	sendNavigate(Route.BASE_MVVM)
    }

    fun moveToAsyncMigratePage() {
	sendNavigate(Route.ASYNC_MIGRATE)
    }

    fun moveToComposeUiPage() {
	sendNavigate(Route.COMPOSE_UI)
    }

    fun moveToMemoComposePage() {
	sendNavigate(Route.COMPOSE_UI.getUri {
	    addQuery(RouteParamsKey.TYPE, "memo")
	})
    }

    fun moveToPermissionsResultPage() {
	sendNavigate(Route.COMPOSE_PERMISSIONS_RESULT)
    }

    fun moveToComposeNavigationPage() {
	sendNavigate(Route.COMPOSE_NAVIGATION)
    }

    fun moveToRoomObserverPage() {
	sendNavigate(Route.ROOM_OBSERVER)
    }

    override fun onIntent() {
	super.onIntent()
	val deeplink = savedStateHandle.get<Uri>(IntentKey.DEEPLINK_URI)
	sendNavigate(deeplink)
    }
}

import android.net.Uri
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.IntentKey
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.RouteParamsKey
import com.hmju.core_navigator.addQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/26
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ActivityViewModel() {

    fun moveToNetworkPage() {
	sendNavigate(Route.NETWORK.getUri {
	    addQuery(RouteParamsKey.TARGET, "root")
	})
    }

    fun moveToRecyclerViewPage() {
	sendNavigate(Route.RECYCLERVIEW)
    }

    fun moveToMvvmLifecyclePage() {
	sendNavigate(Route.BASE_MVVM)
    }

    fun moveToAsyncMigratePage() {
	sendNavigate(Route.ASYNC_MIGRATE)
    }

    fun moveToComposeUiPage() {
	sendNavigate(Route.COMPOSE_UI)
    }

    fun moveToMemoComposePage() {
	sendNavigate(Route.COMPOSE_UI.getUri {
	    addQuery(RouteParamsKey.TYPE, "memo")
	})
    }

    fun moveToPermissionsResultPage() {
	sendNavigate(Route.COMPOSE_PERMISSIONS_RESULT)
    }

    fun moveToComposeNavigationPage() {
	sendNavigate(Route.COMPOSE_NAVIGATION)
    }

    fun moveToRoomObserverPage() {
	sendNavigate(Route.ROOM_OBSERVER)
    }

    override fun onIntent() {
	super.onIntent()
	val deeplink = savedStateHandle.get<Uri>(IntentKey.DEEPLINK_URI)
	sendNavigate(deeplink)
    }
}
