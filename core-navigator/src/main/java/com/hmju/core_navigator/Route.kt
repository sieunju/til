package com.hmju.core_navigator

/**
 * Description :
 *
 * Created by juhongmin on 2025. 10. 19.
 */
sealed interface Route {
	abstract val path: String

	fun matches(path: String): Boolean
}