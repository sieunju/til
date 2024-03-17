package com.features.compose_ui.impl

import android.content.Context
import android.content.Intent
import com.features.compose_ui.ComposeUiActivity
import com.features.compose_ui.MemoComposeUiActivity
import com.features.compose_ui_bridge.ComposeUiBridge
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Description : Compose UI 구현체 클래스
 *
 * Created by juhongmin on 2023/07/23
 */
internal class ComposeUiBridgeImpl (
    private val context: Context
) : ComposeUiBridge {
    override fun moveToPage() {
        Intent(context, ComposeUiActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }

    override fun moveToMemoPage() {
        Intent(context, MemoComposeUiActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}
