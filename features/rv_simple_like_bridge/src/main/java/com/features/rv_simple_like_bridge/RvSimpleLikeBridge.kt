package com.features.rv_simple_like_bridge

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
interface RvSimpleLikeBridge {
    fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager)
}
