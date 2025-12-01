package com.hmju.core_navigator

import android.net.Uri

/**
 * Description : Navigator Core Extensions
 *
 * Created by juhongmin on 2025. 12. 1.
 */

inline fun <reified T : Any> Uri.Builder.addQuery(key: String, value: T) {
    if (value is String) {
	appendQueryParameter(key, value)
    } else if (value is Number) {
	appendQueryParameter(key, value.toString())
    } else if (value is Boolean) {
	appendQueryParameter(key, value.toString())
    } else {
	appendQueryParameter(key, value.toString())
    }
}
