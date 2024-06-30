package com.features.network_expired_token.impl

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.features.network_expired_token.RefreshTokenFragment
import com.features.network_expired_token_bridge.NetworkExpiredTokenBridge

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
 */
internal class NetworkExpiredTokenBridgeImpl : NetworkExpiredTokenBridge {
    override fun moveToPage(layoutId: Int, fm: FragmentManager) {
        fm.commit { replace(layoutId, RefreshTokenFragment()) }
    }
}
