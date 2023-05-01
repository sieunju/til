package com.feature.async_migrate.impl

import android.content.Context
import android.content.Intent
import com.feature.async_migrate.AsyncMigrateActivity
import com.feature.async_migrate_bridge.AsyncMigrateBridge
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AsyncMigrateBridgeImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AsyncMigrateBridge{
    override fun moveToAsyncMigrate() {
        Intent(context,AsyncMigrateActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}
