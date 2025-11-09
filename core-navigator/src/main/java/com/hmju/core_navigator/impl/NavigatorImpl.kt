package com.hmju.core_navigator.impl

import android.net.Uri
import androidx.annotation.MainThread
import com.hmju.core_navigator.Navigator
import com.hmju.core_navigator.Router
import com.hmju.core_navigator.RouterResult
import javax.inject.Inject

/**
 * Description : Navigation Core Handler
 *
 * Created by juhongmin on 2025. 11. 2.
 */
internal class NavigatorImpl @Inject constructor(
    private val processors: Map<String, @JvmSuppressWildcards Router>
) : Navigator {

    @MainThread
    override fun navigate(uri: Uri): RouterResult {
	val path = uri.path ?: return RouterResult.Fail("Path is Null")
	processors.forEach { entry ->
	    val router = entry.value
	    try {
		if (router.matches(path)) {
		    return router.execute(path, uri.toQueryMap())
		}
	    } catch (ex: Exception) {
		return RouterResult.Fail(ex.message ?: "Error!")
	    }

	}
	return RouterResult.Fail("Undefined path.")
    }

    private fun Uri.toQueryMap(): Map<String, String> {
	return try {
	    queryParameterNames
		.filterNot { it.isNullOrBlank() }
		.associateWith { key ->
		    getQueryParameter(key).orEmpty()
		}
	} catch (e: Exception) {
	    emptyMap()
	}
    }
}
