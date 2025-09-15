package com.features.base_mvvm_lifecycle.impl

import android.content.Context
import android.content.Intent
import com.features.base_mvvm_lifecycle.ui.test_1.MvvmLifecycleTestActivity
import com.features.base_mvvm_lifecycle_bridge.BaseMvvmLifecycleBridge
import com.features.base_mvvm_lifecycle_bridge.SerializableEntity

/**
 * Description :
 *
 * Created by juhongmin on 3/14/24
 */
internal class BaseMvvmLifecycleBridgeImpl(
    private val context: Context
) : BaseMvvmLifecycleBridge {
    override fun moveToPage(model: SerializableEntity) {
        Intent(context, MvvmLifecycleTestActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra("Serializable", model)
            context.startActivity(this)
        }
    }
}
