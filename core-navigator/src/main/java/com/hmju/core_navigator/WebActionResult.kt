package com.hmju.core_navigator

/**
 * Description : 
 *
 * Created by juhongmin on 2026. 4. 26.
 */
sealed interface WebActionResult {
    data class Callback(
	val dataMap: Map<String, String> = mapOf()
    ) : WebActionResult

    data object Skip : WebActionResult
}
