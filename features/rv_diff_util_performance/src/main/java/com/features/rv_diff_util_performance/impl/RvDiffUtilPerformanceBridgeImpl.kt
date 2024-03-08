package com.features.rv_diff_util_performance.impl

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.features.rv_diff_util_performance.DiffUtilPerformanceFragment
import com.features.rv_diff_util_performance_bridge.RvDiffUtilPerformanceBridge

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
 */
internal class RvDiffUtilPerformanceBridgeImpl : RvDiffUtilPerformanceBridge {
    override fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager) {
        fm.commit { replace(layoutId, DiffUtilPerformanceFragment()) }
    }
}
