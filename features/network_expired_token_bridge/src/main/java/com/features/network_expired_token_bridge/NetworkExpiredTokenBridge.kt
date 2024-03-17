package com.features.network_expired_token_bridge

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
 */
interface NetworkExpiredTokenBridge {
    fun moveToPage(@IdRes layoutId: Int, fm: FragmentManager)
}
