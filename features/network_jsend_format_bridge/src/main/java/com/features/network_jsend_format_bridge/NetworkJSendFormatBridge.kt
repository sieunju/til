package com.features.network_jsend_format_bridge

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
 */
interface NetworkJSendFormatBridge {
    fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager)
}
