package com.hmju.core.permission.impl

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.hmju.core.permission.PermissionProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 4/5/24
 */
internal class PermissionProviderImpl @Inject constructor(
    @ApplicationContext val context: Context
) : PermissionProvider {
    override fun isGrated(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun isAllGrated(list: List<String>): Boolean {
        var isAllGrated = true
        for (permission in list) {
            if (!isGrated(permission)) {
                isAllGrated = false
                break
            }
        }
        return isAllGrated
    }

    override fun isAllGrated(map: Map<String, Boolean>): Boolean {
        var isAllGrated = true
        for ((_, value) in map) {
            if (!value) {
                isAllGrated = false
                break
            }
        }
        return isAllGrated
    }

    override fun moveToSetting() {
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:${context.packageName}")
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            addCategory(Intent.CATEGORY_DEFAULT)
            context.startActivity(this)
        }
    }
}