package com.features.base_mvvm_bottom_sheet.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.features.base_mvvm_bottom_sheet.BR
import com.features.base_mvvm_bottom_sheet.R
import com.features.base_mvvm_bottom_sheet.databinding.DRefactorBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.hmju.core.ui.base.BaseBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Description : [ChildBottomSheetBlueFragment], [ChildBottomSheetRedFragment]
 *
 * Created by juhongmin on 2022/04/18
 */
@AndroidEntryPoint
internal class RefactorBottomSheetDialog :
    BaseBottomSheetDialog<DRefactorBottomSheetBinding, RefactorBottomSheetViewModel>(
        R.layout.d_refactor_bottom_sheet
    ) {

    override val bindingVariable = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = initViewModel()
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, com.hmju.core.R.style.BottomSheetDialog)
    }

    override fun onShow(dialogInterface: DialogInterface) {
        super.onShow(dialogInterface)
        val behavior = setFullHeightBottomSheet(dialogInterface)
        behavior?.state = BottomSheetBehavior.STATE_EXPANDED
        behavior?.skipCollapsed = false
        behavior?.isDraggable = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            vp.isSaveEnabled = false
            val adapter = PagerAdapter(this@RefactorBottomSheetDialog)
            vp.adapter = adapter
        }

        with(viewModel) {
            startDismiss.observe(viewLifecycleOwner) {
                dismiss()
            }
        }
    }


    class PagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
        override fun getItemCount() = 2

        override fun createFragment(pos: Int): Fragment {
            return when (pos) {
                0 -> ChildBottomSheetBlueFragment.newInstance()
                else -> ChildBottomSheetRedFragment.newInstance()
            }
        }
    }
}
