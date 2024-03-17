package com.features.base_mvvm_lifecycle.ui.test_2

import com.features.base_mvvm_lifecycle.BR
import com.features.base_mvvm_lifecycle.R
import com.features.base_mvvm_lifecycle.databinding.AMvvmLifecycleTest2Binding
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MvvmLifecycleTest2Activity : BaseActivity<AMvvmLifecycleTest2Binding, MvvmLifecycleTest2ViewModel>(
    R.layout.a_mvvm_lifecycle_test2
) {

    override val viewModel: MvvmLifecycleTest2ViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm
}
