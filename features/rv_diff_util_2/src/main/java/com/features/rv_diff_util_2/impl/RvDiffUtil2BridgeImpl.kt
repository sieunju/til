package com.features.rv_diff_util_2.impl

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.features.rv_diff_util_2.ui.DiffUtil2Fragment
import com.features.rv_diff_util_2_bridge.RvDiffUtil2Bridge

/**
 * Description :
 *
 * Created by juhongmin on 3/10/24
 */
class RvDiffUtil2BridgeImpl : RvDiffUtil2Bridge {
    override fun moveToPage(layoutId: Int, fm: FragmentManager) {
        fm.commit {
            replace(layoutId, DiffUtil2Fragment())
            addToBackStack("DiffUtil2Fragment")
        }
    }
}
