package com.hmju.core_navigator

/**
 * Description : Base Router Class
 *
 * Created by juhongmin on 2025. 10. 19.
 */
abstract class Router {
    abstract fun route(): Route
    abstract fun matches(path: String): Boolean
    abstract fun execute(path: String, params: Map<String, String>): RouterResult
}
