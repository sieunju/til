package com.features.base_mvvm_bottom_sheet_bridge

import androidx.fragment.app.FragmentManager

/**
 * Description :
 *
 * Created by juhongmin on 3/14/24
 */
interface BaseMvvmBottomSheetBridge {
    fun showBottomSheet(fm: FragmentManager)
    fun showShareBottomSheet(fm: FragmentManager)
}
