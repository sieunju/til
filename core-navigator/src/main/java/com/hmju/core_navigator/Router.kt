package com.hmju.core_navigator

/**
 * Description : Base Router Class
 *
 * Created by juhongmin on 2025. 10. 19.
 */
sealed interface Router {
    fun matches(path: String): Boolean
    fun execute(path: String, params: Map<String, String>): RouterResult
}
