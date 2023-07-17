package com.features.network

import android.os.Bundle
import com.features.network.databinding.ActivityNetworkBinding
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NetworkActivity :
    BaseActivity<ActivityNetworkBinding, ActivityViewModel>(R.layout.activity_network) {

    override val viewModel: ActivityViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(com.hmju.core.R.style.AppTheme)
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, NetworkFragment())
            addToBackStack(null)
            commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}