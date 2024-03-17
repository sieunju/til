package com.features.base_mvvm.impl

import android.content.Context
import android.content.Intent
import com.features.base_mvvm.RefactorBaseTestActivity
import com.features.base_mvvm_bridge.BaseMvvmBridge
import com.hmju.core.ui.base.IntentKey

/**
 * Description : Base Mvvm Lifecycle 구현체 클래스
 *
 * Created by juhongmin on 2022/07/24
 */
internal class BaseMvvmBridgeImpl(
    private val context: Context
) : BaseMvvmBridge {

    override fun moveToPage() {
        Intent(context, RefactorBaseTestActivity::class.java).apply {
            putExtra(IntentKey.TOKEN, "randomToken")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}
