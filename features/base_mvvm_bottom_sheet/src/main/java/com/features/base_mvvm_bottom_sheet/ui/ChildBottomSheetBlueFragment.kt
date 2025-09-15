package com.features.base_mvvm_bottom_sheet.ui

import android.os.Bundle
import android.view.View
import com.features.base_mvvm_bottom_sheet.BR
import com.features.base_mvvm_bottom_sheet.R
import com.features.base_mvvm_bottom_sheet.databinding.FChildBottomSheetBlueBinding
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

    private val parentViewModel: RefactorBottomSheetViewModel by lazy {
        parentViewModel(
            requireParentFragment()
        )
    }

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
