package com.features.recyclerview.impl

import android.content.Context
import android.content.Intent
import com.features.recyclerview.RecyclerViewActivity
import com.features.recyclerview_bridge.RecyclerViewBridge
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Description : RecyclerView Requirements 구현체 클래스
 *
 * Created by juhongmin on 2022/07/24
 */
internal class RecyclerViewBridgeImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : RecyclerViewBridge {

    override fun moveToPage() {
        Intent(context, RecyclerViewActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}
