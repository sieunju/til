package com.hmju.compose_permissions_result.impl

import android.content.Context
import android.content.Intent
import com.hmju.compose_permissions_result.ComposePermissionsResultActivity
import com.hmju.compose_permissions_result_bridge.ComposePermissionsResultBridge

/**
 * Description :
 *
 * Created by juhongmin on 4/3/24
 */
internal class ComposePermissionsResultBridgeImpl(
    private val context: Context
) : ComposePermissionsResultBridge {
    override fun moveToPage() {
        Intent(context, ComposePermissionsResultActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}
