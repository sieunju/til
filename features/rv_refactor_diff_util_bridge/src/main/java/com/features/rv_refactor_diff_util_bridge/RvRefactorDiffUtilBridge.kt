package com.features.rv_refactor_diff_util_bridge

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
 */
interface RvRefactorDiffUtilBridge {
    fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager)
}
