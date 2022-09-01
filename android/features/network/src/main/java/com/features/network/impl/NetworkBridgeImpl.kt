package com.features.network.impl

import android.content.Context
import android.content.Intent
import com.features.network.NetworkActivity
import com.features.network_bridge.NetworkBridge
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Description : Network Requirements 구현체 클래스
 *
 * Created by juhongmin on 2022/07/22
 */
internal class NetworkBridgeImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NetworkBridge {
    override fun moveToNetworkPage() {
        Intent(context, NetworkActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}
