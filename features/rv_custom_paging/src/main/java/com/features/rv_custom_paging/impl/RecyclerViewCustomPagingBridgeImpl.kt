package com.features.rv_custom_paging.impl

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.features.rv_custom_paging.ui.CustomPagingFragment
import com.features.rv_custom_paging_bridge.RecyclerViewCustomPagingBridge

/**
 * Description : RecyclerView CustomPagingBridge Impl Class
 *
 * Created by juhongmin on 2/15/24
 */
internal class RecyclerViewCustomPagingBridgeImpl : RecyclerViewCustomPagingBridge {
    override fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager) {
        fm.commit { replace(layoutId, CustomPagingFragment()) }
    }
}
