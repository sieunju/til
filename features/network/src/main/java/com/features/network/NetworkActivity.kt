package com.features.network

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.lifecycle.lifecycleScope
import com.features.network.databinding.ActivityNetworkBinding
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.BaseActivity
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.RouteParamsKey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class NetworkActivity : BaseActivity<ActivityNetworkBinding, ActivityViewModel>(
    R.layout.activity_network
) {

    override val viewModel: ActivityViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private val onBackPressCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
	override fun handleOnBackPressed() {
	    if (supportFragmentManager.backStackEntryCount > 1) {
		supportFragmentManager.popBackStack()
	    } else {
		finish()
	    }
	}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	handleRoute(intent)
	onBackPressedDispatcher.addCallback(this, onBackPressCallback)
    }

    override fun onNewIntent(intent: Intent?) {
	super.onNewIntent(intent)
	handleRoute(intent)
	setIntent(null)
    }

    private fun handleRoute(intent: Intent?) {
	if (intent == null) return
	val route = Route.from(intent.getStringExtra(RouteParamsKey.PATH)) ?: return
	supportFragmentManager.commit(
	    allowStateLoss = true
	) {
	    replace(R.id.fragment, NetworkFragment())
	    addToBackStack(null)
	}
	lifecycleScope.launch {
	    delay(500)
	    if (route == Route.NETWORK_ERROR_HANDLING) {
		viewModel.sendNavigate(route.getUri {
		    appendQueryParameter(RouteParamsKey.LAYOUT_ID, R.id.fragment.toString())
		    appendQueryParameter(RouteParamsKey.IS_INTERNAL, true.toString())
		})
	    } else if (route == Route.NETWORK_EXPIRED_TOKEN) {
		viewModel.sendNavigate(route.getUri {
		    appendQueryParameter(RouteParamsKey.LAYOUT_ID, R.id.fragment.toString())
		    appendQueryParameter(RouteParamsKey.IS_INTERNAL, true.toString())
		})
	    } else if (route == Route.NETWORK_JSEND_FORMAT) {
		viewModel.sendNavigate(route.getUri {
		    appendQueryParameter(RouteParamsKey.LAYOUT_ID, R.id.fragment.toString())
		    appendQueryParameter(RouteParamsKey.IS_INTERNAL, true.toString())
		})
	    }
	}
    }
}