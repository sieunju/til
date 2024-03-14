package com.features.base_mvvm_lifecycle.ui.test_3

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import com.features.base_mvvm_lifecycle.BR
import com.features.base_mvvm_lifecycle.R
import com.features.base_mvvm_lifecycle.databinding.AMvvmLifecycleTest3Binding
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MvvmLifecycleTest3Activity :
    BaseActivity<AMvvmLifecycleTest3Binding, MvvmLifecycleTest3ViewModel>(
        R.layout.a_mvvm_lifecycle_test3
    ) {

    override val viewModel: MvvmLifecycleTest3ViewModel by viewModels()
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
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, MvvmLifecycleTest3Fragment())
            addToBackStack(null)
            commit()
        }
        onBackPressedDispatcher.addCallback(this, onBackPressCallback)
    }
}
