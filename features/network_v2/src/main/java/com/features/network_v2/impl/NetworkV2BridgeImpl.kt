package com.features.network_v2.impl

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.features.network_v2.NetworkV2Fragment
import com.features.network_v2_bridge.NetworkV2Bridge

/**
 * Description :
 *
 * Created by juhongmin on 11/22/23
 */
internal class NetworkV2BridgeImpl : NetworkV2Bridge {

    override fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager) {
        fm.commit { replace(layoutId, NetworkV2Fragment()) }
    }
}
