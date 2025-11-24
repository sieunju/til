package com.features.network

import androidx.lifecycle.viewModelScope
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.RouteParamsKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
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
	Timber.d("Route $route")
	viewModelScope.launch {
	    delay(500)
	    when (route) {
		Route.NETWORK_ERROR_HANDLING -> {
		    sendNavigate(route.getUri {
			appendQueryParameter(RouteParamsKey.LAYOUT_ID, R.id.fragment.toString())
			appendQueryParameter(RouteParamsKey.IS_INTERNAL, true.toString())
		    })
		}

		Route.NETWORK_EXPIRED_TOKEN -> {
		    sendNavigate(route.getUri {
			appendQueryParameter(RouteParamsKey.LAYOUT_ID, R.id.fragment.toString())
			appendQueryParameter(RouteParamsKey.IS_INTERNAL, true.toString())
		    })
		}

		Route.NETWORK_JSEND_FORMAT -> {
		    sendNavigate(route.getUri {
			appendQueryParameter(RouteParamsKey.LAYOUT_ID, R.id.fragment.toString())
			appendQueryParameter(RouteParamsKey.IS_INTERNAL, true.toString())
		    })
		}

		else -> Unit
	    }
	}
    }
}