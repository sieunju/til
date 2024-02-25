package com.features.network_error_handling_bridge

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

/**
 * Description :
 *
 * Created by juhongmin on 2/25/24
 */
interface NetworkErrorHandlingBridge {
    fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager)
}
