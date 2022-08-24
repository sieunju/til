package com.features.base_mvvm.ui.mvvm_lifecycle

import android.content.Intent
import android.os.Bundle
import com.features.base_mvvm.BR
import com.features.base_mvvm.R
import com.features.base_mvvm.databinding.ActivityMvvmLifecycleTestBinding
import com.features.core_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MvvmLifecycleTestActivity :
    BaseActivity<ActivityMvvmLifecycleTestBinding, MvvmLifecycleTestViewModel>(
        R.layout.activity_mvvm_lifecycle_test
    ) {

    override val viewModel: MvvmLifecycleTestViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            startMovePageEvent.observe(this@MvvmLifecycleTestActivity) {
                Intent(
                    this@MvvmLifecycleTestActivity,
                    MvvmLifecycleTest2Activity::class.java
                ).apply {
                    this@MvvmLifecycleTestActivity.startActivity(this)
                }
            }
        }
    }
}
