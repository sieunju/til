package com.features.rv_custom_paging_bridge

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

/**
 * Description : RecyclerView Custom Paging Interface
 *
 * Created by juhongmin on 2/15/24
 */
interface RecyclerViewCustomPagingBridge {
    fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager)
}
