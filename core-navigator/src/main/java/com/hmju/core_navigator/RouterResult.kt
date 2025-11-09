package com.hmju.core_navigator

/**
 * Description : Route Result Class
 *
 * Created by juhongmin on 2025. 10. 26.
 */
sealed interface RouterResult {
    data class Success(
	val uid: Long = System.currentTimeMillis()
    ) : RouterResult

    data class Fail(
	val errMsg: String
    ) : RouterResult
}
