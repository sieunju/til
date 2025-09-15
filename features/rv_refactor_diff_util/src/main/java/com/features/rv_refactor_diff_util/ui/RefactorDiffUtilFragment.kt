package com.features.rv_refactor_diff_util.ui

import com.features.rv_refactor_diff_util.BR
import com.features.rv_refactor_diff_util.R
import com.features.rv_refactor_diff_util.databinding.FRefactorDiffUtilBinding
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description : DiffUtil Fragment Type 1
 *
 * Created by juhongmin on 3/9/24
 */
@AndroidEntryPoint
class RefactorDiffUtilFragment : BaseFragment<FRefactorDiffUtilBinding, RefactorDiffUtilViewModel>(
    R.layout.f_refactor_diff_util
) {
    override val viewModel: RefactorDiffUtilViewModel by initViewModel()
    override val bindingVariable: Int get() = BR.vm
}
