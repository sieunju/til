package com.features.recyclerview.ui.diffutil_refactor

import android.os.Bundle
import android.view.View
import com.features.core_ui.base.BaseFragment
import com.features.recyclerview.BR
import com.features.recyclerview.R
import com.features.recyclerview.databinding.FRefactorDiffUtilBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description : DiffUtil Fragment
 *
 * Created by juhongmin on 2022/02/16
 */
@AndroidEntryPoint
class RefactorDiffUtilFragment :
    BaseFragment<FRefactorDiffUtilBinding, RefactorDiffUtilViewModel>(R.layout.f_refactor_diff_util) {

    override val bindingVariable: Int = BR.vm

    override val viewModel: RefactorDiffUtilViewModel by initViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            start()
        }
    }
}