package com.features.async_migrate.impl

import android.content.Context
import android.content.Intent
import com.features.async_migrate.AsyncMigrateActivity
import com.features.async_migrate_bridge.AsyncMigrateBridge

internal class AsyncMigrateBridgeImpl(
    private val context: Context
) : AsyncMigrateBridge {

    override fun moveToPage() {
        Intent(context, AsyncMigrateActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}
