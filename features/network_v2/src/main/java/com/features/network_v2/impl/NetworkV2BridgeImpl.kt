package com.features.network_v2.impl

import androidx.fragment.app.FragmentManager
import com.features.network_v2.NetworkV2Fragment
import com.features.network_v2_bridge.NetworkV2Bridge
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 11/22/23
 */
internal class NetworkV2BridgeImpl @Inject constructor() : NetworkV2Bridge {
    override fun moveToNetworkV2Page(fm: FragmentManager, containerViewId: Int) {
        fm.beginTransaction().apply {
            replace(containerViewId, NetworkV2Fragment())
            addToBackStack(null)
            commit()
        }
    }
}
