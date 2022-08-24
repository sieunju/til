package com.features.base_mvvm.ui.refactor_base.child

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.features.base_mvvm.BR
import com.features.base_mvvm.R
import com.features.base_mvvm.databinding.FChildRefactorBlueBinding
import com.features.base_mvvm.ui.base.RefactorBaseRootTestViewModel
import com.features.base_mvvm.ui.refactor_base.bottomsheet.RefactorBottomSheetDialog
import com.features.base_mvvm.ui.refactor_base.bottomsheet.RefactorSharedBottomSheetDialog
import com.features.core_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description :
 *
 * Created by juhongmin on 2022/04/18
 */
@AndroidEntryPoint
class ChildRefactorBlueFragment
    : BaseFragment<FChildRefactorBlueBinding, ChildRefactorBlueViewModel>(
    R.layout.f_child_refactor_blue
) {
    override val viewModel: ChildRefactorBlueViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private val parentViewModel: RefactorBaseRootTestViewModel by viewModels(
        ownerProducer = { requireParentFragment() }
    )

    private val bottomSheetDialog: RefactorSharedBottomSheetDialog by lazy { RefactorSharedBottomSheetDialog() }

    private val testBottomSheetDialog: RefactorBottomSheetDialog by lazy { RefactorBottomSheetDialog() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.parentVm, parentViewModel)

        with(viewModel) {
            startBottomSheetDialog.observe(viewLifecycleOwner) {
                RefactorBottomSheetDialog()
                    .simpleShow(childFragmentManager)
            }

            startParentBottomSheetDialog.observe(viewLifecycleOwner) {
                bottomSheetDialog.simpleShow(childFragmentManager)
            }
        }
    }

    companion object {
        fun newInstance(): ChildRefactorBlueFragment {
            return ChildRefactorBlueFragment().apply {
                arguments = bundleOf(
                    "KEY" to "${System.currentTimeMillis()}_TTTT"
                )
            }
        }
    }
}