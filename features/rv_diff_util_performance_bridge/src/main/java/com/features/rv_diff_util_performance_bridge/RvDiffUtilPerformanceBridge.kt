package com.features.rv_diff_util_performance_bridge

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
 */
interface RvDiffUtilPerformanceBridge {
    fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager)
}
