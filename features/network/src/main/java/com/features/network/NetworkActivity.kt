package com.features.network

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.commit
import com.features.network.databinding.ActivityNetworkBinding
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NetworkActivity :
    BaseActivity<ActivityNetworkBinding, ActivityViewModel>(R.layout.activity_network) {

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

        supportFragmentManager.commit {
            replace(R.id.fragment, NetworkFragment())
            addToBackStack(null)
        }
        onBackPressedDispatcher.addCallback(this, onBackPressCallback)
    }
}