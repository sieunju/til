package com.features.rv_diff_util_2_bridge

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

/**
 * Description :
 *
 * Created by juhongmin on 3/10/24
 */
interface RvDiffUtil2Bridge {
    fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager)
}
