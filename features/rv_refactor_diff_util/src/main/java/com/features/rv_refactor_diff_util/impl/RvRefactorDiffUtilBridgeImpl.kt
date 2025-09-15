package com.features.rv_refactor_diff_util.impl

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.features.rv_refactor_diff_util.ui.RefactorDiffUtilFragment
import com.features.rv_refactor_diff_util_bridge.RvRefactorDiffUtilBridge

/**
 * Description :
 *
 * Created by juhongmin on 3/9/24
 */
internal class RvRefactorDiffUtilBridgeImpl : RvRefactorDiffUtilBridge {
    override fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager) {
        fm.commit {
            replace(layoutId, RefactorDiffUtilFragment())
            addToBackStack("RefactorDiffUtilFragment")
        }
    }
}
