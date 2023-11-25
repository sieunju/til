package com.hmju.core.pref

/**
 * Description :
 *
 * Created by juhongmin on 11/24/23
 */
interface PreferenceManager {
    fun getString(key: String): String
    fun getLong(key: String): Long
    fun <T> setValue(key: String, value: T)

    companion object {
        const val KEY_TOKEN_EXPIRED_MS = "token_expired_ms"
    }
}