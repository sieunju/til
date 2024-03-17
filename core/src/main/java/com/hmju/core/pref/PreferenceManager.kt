package com.hmju.core.pref

import android.content.SharedPreferences

/**
 * Description :
 *
 * Created by juhongmin on 11/24/23
 */
interface PreferenceManager {

    fun getPref(): SharedPreferences
    fun getString(key: String): String
    fun getLong(key: String): Long
    fun <T> setValue(key: String, value: T)

    companion object {
        const val KEY_TOKEN_EXPIRED_MS = "til_access_token_expired_at"
        const val KEY_TOKEN = "til_access_token"
        const val KEY_REFRESH_TOKEN = "til_refresh_token"
    }
}