package com.features.network_v2_bridge

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

interface NetworkV2Bridge {
    fun moveToNetworkV2Page(fm: FragmentManager, @IdRes containerViewId: Int)
}
