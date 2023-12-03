package com.hmju.core.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Description : SharedPreferences Manager
 *
 * Created by juhongmin on 11/24/23
 */
class PreferenceManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PreferenceManager {

    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences("til_pref", Context.MODE_PRIVATE)
    }

    override fun getPref(): SharedPreferences {
        return preferences
    }

    override fun getString(key: String): String {
        return preferences.getString(key, "") ?: ""
    }

    override fun getLong(key: String): Long {
        return preferences.getLong(key, 0)
    }

    override fun <T> setValue(key: String, value: T) {
        preferences.edit {
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
