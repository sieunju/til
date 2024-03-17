package com.features.network_jsend_format.impl

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.features.network_jsend_format.JsonJsendFragment
import com.features.network_jsend_format_bridge.NetworkJSendFormatBridge

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
 */
internal class NetworkJSendFormatBridgeImpl : NetworkJSendFormatBridge {
    override fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager) {
        fm.commit { replace(layoutId, JsonJsendFragment()) }
    }
}
