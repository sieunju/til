package com.hmju.core_navigator

import android.content.Context

/**
 * Description : Base Router Class
 *
 * Created by juhongmin on 2025. 10. 19.
 */
abstract class Router {
    abstract fun route(): Route
    abstract fun matches(path: String): Boolean
    // // android.util.AndroidRuntimeException: Calling startActivity() from outside of an
    // Activity context requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?
    // Activity Context 매개변수로 추가
    abstract fun execute(context: Context,path: String, params: Map<String, String>): RouterResult
}
