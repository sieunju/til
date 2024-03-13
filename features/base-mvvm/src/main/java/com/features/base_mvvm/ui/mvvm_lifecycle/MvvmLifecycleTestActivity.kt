package com.features.base_mvvm.ui.mvvm_lifecycle

import com.features.base_mvvm.BR
import com.features.base_mvvm.R
import com.features.base_mvvm.databinding.ActivityMvvmLifecycleTestBinding
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MvvmLifecycleTestActivity :
    BaseActivity<ActivityMvvmLifecycleTestBinding, MvvmLifecycleTestViewModel>(
        R.layout.activity_mvvm_lifecycle_test
    ) {

    override val viewModel: MvvmLifecycleTestViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm
}
