package com.features.base_mvvm.ui.mvvm_lifecycle

import android.content.Intent
import android.os.Bundle
import com.features.base_mvvm.R
import com.features.base_mvvm.BR
import com.features.base_mvvm.databinding.ActivityMvvmLifecycleTest2Binding
import com.features.core_ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MvvmLifecycleTest2Activity
    : BaseActivity<ActivityMvvmLifecycleTest2Binding, MvvmLifecycleTest2ViewModel>(
    R.layout.activity_mvvm_lifecycle_test2
) {

    override val viewModel: MvvmLifecycleTest2ViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            startMovePageEvent.observe(this@MvvmLifecycleTest2Activity) {
                Intent(
                    this@MvvmLifecycleTest2Activity,
                    MvvmLifecycleTest3Activity::class.java
                ).apply {
                    this@MvvmLifecycleTest2Activity.startActivity(this)
                }
            }
        }
    }
}
