package com.features.base_mvvm.ui.refactor_base.bottomsheet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.features.base_mvvm.BR
import com.features.base_mvvm.R
import com.features.base_mvvm.databinding.FChildBottomSheetBlueBinding
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description :
 *
 * Created by juhongmin on 2022/04/18
 */
@AndroidEntryPoint
class ChildBottomSheetBlueFragment
    : BaseFragment<FChildBottomSheetBlueBinding, ChildBottomSheetBlueViewModel>(
    R.layout.f_child_bottom_sheet_blue
) {
    override val viewModel: ChildBottomSheetBlueViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private val parentViewModel: RefactorBottomSheetViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.parentVm, parentViewModel)
    }

    companion object {
        fun newInstance(): ChildBottomSheetBlueFragment {
            return ChildBottomSheetBlueFragment()
        }
    }
}
