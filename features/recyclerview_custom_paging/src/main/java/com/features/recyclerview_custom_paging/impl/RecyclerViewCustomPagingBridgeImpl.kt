package com.features.recyclerview_custom_paging.impl

import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.features.recyclerview_custom_paging.ui.CustomPagingFragment
import com.features.recyclerview_custom_paging_bridge.RecyclerViewCustomPagingBridge
import javax.inject.Inject

/**
 * Description : RecyclerView CustomPagingBridge Impl Class
 *
 * Created by juhongmin on 2/15/24
 */
internal class RecyclerViewCustomPagingBridgeImpl @Inject constructor(

) : RecyclerViewCustomPagingBridge {
    override fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager) {
        fm.commit {
            replace(layoutId,CustomPagingFragment())
        }
    }
}
