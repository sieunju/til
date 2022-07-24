package com.features.base_mvvm.ui.mvvm_lifecycle

import android.os.Bundle
import androidx.activity.viewModels
import com.features.base_mvvm.BR
import com.features.base_mvvm.R
import com.features.base_mvvm.databinding.ActivityMvvmLifecycleTest3Binding
import com.hmju.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MvvmLifecycleTest3Activity
    : BaseActivity<ActivityMvvmLifecycleTest3Binding, MvvmLifecycleTest3ViewModel>(
    R.layout.activity_mvvm_lifecycle_test3
) {

    override val viewModel: MvvmLifecycleTest3ViewModel by viewModels()
    override val bindingVariable: Int = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, MvvmLifecycleTest3Fragment())
            addToBackStack(null)
            commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}
