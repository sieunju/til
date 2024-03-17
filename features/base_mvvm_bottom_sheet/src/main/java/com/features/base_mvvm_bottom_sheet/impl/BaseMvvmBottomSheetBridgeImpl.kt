package com.features.base_mvvm_bottom_sheet.impl

import androidx.fragment.app.FragmentManager
import com.features.base_mvvm_bottom_sheet.ui.RefactorBottomSheetDialog
import com.features.base_mvvm_bottom_sheet.ui.RefactorSharedBottomSheetDialog
import com.features.base_mvvm_bottom_sheet_bridge.BaseMvvmBottomSheetBridge

/**
 * Description :
 *
 * Created by juhongmin on 3/14/24
 */
internal class BaseMvvmBottomSheetBridgeImpl : BaseMvvmBottomSheetBridge {
    override fun showBottomSheet(fm: FragmentManager) {
        RefactorBottomSheetDialog()
            .simpleShow(fm, "RefactorBottomSheetDialog")
    }

    override fun showShareBottomSheet(fm: FragmentManager) {
        RefactorSharedBottomSheetDialog()
            .simpleShow(fm, "RefactorSharedBottomSheetDialog")
    }
}
