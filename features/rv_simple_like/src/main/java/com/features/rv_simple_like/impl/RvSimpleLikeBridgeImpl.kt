package com.features.rv_simple_like.impl

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.features.rv_simple_like.SimpleLikeFragment
import com.features.rv_simple_like_bridge.RvSimpleLikeBridge

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
internal class RvSimpleLikeBridgeImpl : RvSimpleLikeBridge {
    override fun moveToPage(layoutId: Int, fm: FragmentManager) {
        fm.commit {
            replace(layoutId, SimpleLikeFragment())
            addToBackStack("SimpleLikeFragment")
        }
    }
}
