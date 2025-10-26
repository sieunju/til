package com.hmju.core_navigator

/**
 * Description :
 *
 * Created by juhongmin on 2025. 10. 19.
 */
interface Navigator {
	fun matches(route: Route): Boolean
}