package com.features.network

import androidx.lifecycle.viewModelScope
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.RouteParamsKey
import com.hmju.core_navigator.addQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2025. 11. 24.
 */
@HiltViewModel
class NetworkRootViewModel @Inject constructor() : ActivityViewModel() {

    override fun onIntent() {
	super.onIntent()
	val targetPath = getBundleData().getString(RouteParamsKey.PATH)
	if (targetPath.isNullOrEmpty()) return
	val route = Route.from(targetPath)
	viewModelScope.launch {
	    delay(500)
	    when (route) {
		Route.NETWORK_ERROR_HANDLING -> {
		    sendNavigate(route.getUri {
			addQuery(RouteParamsKey.LAYOUT_ID, R.id.fragment)
			addQuery(RouteParamsKey.IS_INTERNAL, true)
		    })
		}

		Route.NETWORK_EXPIRED_TOKEN -> {
		    sendNavigate(route.getUri {
			addQuery(RouteParamsKey.LAYOUT_ID, R.id.fragment)
			addQuery(RouteParamsKey.IS_INTERNAL, true)
		    })
		}

		Route.NETWORK_JSEND_FORMAT -> {
		    sendNavigate(route.getUri {
			addQuery(RouteParamsKey.LAYOUT_ID, R.id.fragment)
			addQuery(RouteParamsKey.IS_INTERNAL, true)
		    })
		}

		Route.NETWORK_REFACTOR_V2 -> {
		    sendNavigate(route.getUri {
			addQuery(RouteParamsKey.LAYOUT_ID, R.id.fragment)
			addQuery(RouteParamsKey.IS_INTERNAL, true)
		    })
		}

		else -> Unit
	    }
	}
    }
}