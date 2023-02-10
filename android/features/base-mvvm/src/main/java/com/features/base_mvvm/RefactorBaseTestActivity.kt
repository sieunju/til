package com.features.base_mvvm

import com.features.base_mvvm.databinding.ARefactorBaseTestBinding
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description : StartResult Test 용 액티비티
 *
 * Created by juhongmin on 2022/03/19
 */
@AndroidEntryPoint
class RefactorBaseTestActivity : BaseActivity<ARefactorBaseTestBinding, RefactorTestViewModel>
    (R.layout.a_refactor_base_test) {

    override val viewModel: RefactorTestViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm
}
