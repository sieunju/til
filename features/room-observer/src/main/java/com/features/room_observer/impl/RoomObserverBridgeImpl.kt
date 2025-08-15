package com.features.room_observer.impl

import android.content.Context
import android.content.Intent
import com.features.room_observer.RoomObserverActivity
import com.features.room_observer_bridge.RoomObserverBridge

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
internal class RoomObserverBridgeImpl(
    private val context: Context
) : RoomObserverBridge {
    override fun moveToPage() {
        Intent(context, RoomObserverActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}
