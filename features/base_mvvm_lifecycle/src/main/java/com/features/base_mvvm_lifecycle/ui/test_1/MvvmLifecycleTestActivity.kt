package com.features.base_mvvm_lifecycle.ui.test_1

import com.features.base_mvvm_lifecycle.BR
import com.features.base_mvvm_lifecycle.R
import com.features.base_mvvm_lifecycle.databinding.AMvvmLifecycleTestBinding
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MvvmLifecycleTestActivity :
    BaseActivity<AMvvmLifecycleTestBinding, MvvmLifecycleTestViewModel>(
        R.layout.a_mvvm_lifecycle_test
    ) {

    override val viewModel: MvvmLifecycleTestViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm
}
