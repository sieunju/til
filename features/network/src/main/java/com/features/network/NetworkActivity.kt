package com.features.network

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import com.features.network.databinding.ActivityNetworkBinding
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NetworkActivity : BaseActivity<ActivityNetworkBinding, NetworkRootViewModel>(
    R.layout.activity_network
) {

    override val viewModel: NetworkRootViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private val onBackPressCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
	override fun handleOnBackPressed() {
	    if (supportFragmentManager.backStackEntryCount > 0) {
		supportFragmentManager.popBackStack()
	    } else {
		finish()
	    }
	}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	supportFragmentManager.commitNow {
	    replace(R.id.fragment, NetworkFragment())
	}
	onBackPressedDispatcher.addCallback(this, onBackPressCallback)
    }
}