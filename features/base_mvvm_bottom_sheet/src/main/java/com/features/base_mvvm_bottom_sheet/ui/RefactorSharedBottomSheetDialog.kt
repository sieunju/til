package com.features.base_mvvm_bottom_sheet.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.features.base_mvvm_bottom_sheet.R
import com.features.base_mvvm_bottom_sheet.BR
import com.features.base_mvvm_bottom_sheet.databinding.DRefactorBottomSheetBinding
import com.hmju.core.ui.base.BaseSharedBottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Description : [ChildBottomSheetBlueFragment]. [ChildBottomSheetRedFragment]
 *
 * Created by juhongmin on 2022/04/19
 */
@AndroidEntryPoint
internal class RefactorSharedBottomSheetDialog
    : BaseSharedBottomSheetDialog<DRefactorBottomSheetBinding, RefactorBottomSheetViewModel>(
    R.layout.d_refactor_bottom_sheet
) {

    override val bindingVariable = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = initBottomSheetViewModel()
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, com.hmju.core.R.style.BottomSheetDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            vp.offscreenPageLimit = 2
            vp.isSaveEnabled = false
            val adapter = PagerAdapter(this@RefactorSharedBottomSheetDialog)
            vp.adapter = adapter
        }

        with(viewModel) {
            startDismiss.observe(viewLifecycleOwner) {
                dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
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

