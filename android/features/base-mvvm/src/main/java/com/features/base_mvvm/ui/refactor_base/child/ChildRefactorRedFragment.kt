package com.features.base_mvvm.ui.refactor_base.child

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.features.base_mvvm.BR
import com.features.base_mvvm.R
import com.features.base_mvvm.databinding.FChildRefactorRedBinding
import com.features.base_mvvm.ui.base.RefactorBaseRootTestViewModel
import com.hmju.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description :
 *
 * Created by juhongmin on 2022/04/18
 */
@AndroidEntryPoint
class ChildRefactorRedFragment : BaseFragment<FChildRefactorRedBinding, ChildRefactorRedViewModel>(
    R.layout.f_child_refactor_red
) {
    override val viewModel: ChildRefactorRedViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private val parentViewModel: RefactorBaseRootTestViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.parentVm, parentViewModel)
    }
}
