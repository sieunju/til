package com.hmju.core.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 11/24/23
 */
class PreferenceManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PreferenceManager {

    private val pref = context.getSharedPreferences("til_pref", Context.MODE_PRIVATE)
    override fun getString(key: String): String {
        return pref.getString(key, "") ?: ""
    }

    override fun getLong(key: String): Long {
        return pref.getLong(key, 0)
    }

    override fun <T> setValue(key: String, value: T) {
        pref.edit {
            when (value) {
                is String -> putString(key, value)
                is Long -> putLong(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                else -> {}
            }
            apply()
        }
    }
}