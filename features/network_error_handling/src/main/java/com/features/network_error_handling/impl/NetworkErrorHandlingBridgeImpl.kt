package com.features.network_error_handling.impl

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.features.network_error_handling.ErrorHandlingFragment
import com.features.network_error_handling_bridge.NetworkErrorHandlingBridge

/**
 * Description :
 *
 * Created by juhongmin on 2/25/24
 */
internal class NetworkErrorHandlingBridgeImpl : NetworkErrorHandlingBridge {
    override fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager) {
        fm.commit { replace(layoutId, ErrorHandlingFragment()) }
    }
}
