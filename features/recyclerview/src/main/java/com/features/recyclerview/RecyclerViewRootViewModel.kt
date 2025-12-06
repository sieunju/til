package com.features.recyclerview

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
 * Description : RecyclerView 공부한 내용의 Activity Root ViewModel
 *
 * Created by juhongmin on 2025. 12. 2.
 */
@HiltViewModel
class RecyclerViewRootViewModel @Inject constructor() : ActivityViewModel() {

    override fun onIntent() {
	super.onIntent()
	val targetPath = getBundleData().getString(RouteParamsKey.PATH)
	if (targetPath.isNullOrEmpty()) return
	val route = Route.from(targetPath)
	viewModelScope.launch {
	    delay(200)
	    when (route) {
		Route.RECYCLERVIEW_PAGING -> {
		    sendNavigate(route.getUri {
			addQuery(RouteParamsKey.LAYOUT_ID, R.id.fragment)
			addQuery(RouteParamsKey.IS_INTERNAL, true)
		    })
		}

		Route.RECYCLERVIEW_SIMPLE_LIKE -> {
		    sendNavigate(route.getUri {
			addQuery(RouteParamsKey.LAYOUT_ID, R.id.fragment)
			addQuery(RouteParamsKey.IS_INTERNAL, true)
		    })
		}

		Route.RECYCLERVIEW_DIFF_UTIL_PERFORMANCE -> {
		    sendNavigate(route.getUri {
			addQuery(RouteParamsKey.LAYOUT_ID, R.id.fragment)
			addQuery(RouteParamsKey.IS_INTERNAL, true)
		    })
		}

		Route.RECYCLERVIEW_REFACTOR_DIFF_UTIL -> {
		    sendNavigate(route.getUri {
			addQuery(RouteParamsKey.LAYOUT_ID, R.id.fragment)
			addQuery(RouteParamsKey.IS_INTERNAL, true)
		    })
		}

		Route.RECYCLERVIEW_DIFF_UTIL_2 -> {
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