package com.features.compose_navigation.impl

import android.content.Context
import android.content.Intent
import com.features.compose_navigation.ComposeNavigationActivity
import com.features.compose_navigation_bridge.ComposeNavigationBridge

/**
 * Description :
 *
 * Created by juhongmin on 4/6/24
 */
internal class ComposeNavigationBridgeImpl(
    private val context: Context
) : ComposeNavigationBridge {
    override fun moveToPage() {
        Intent(context, ComposeNavigationActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}
