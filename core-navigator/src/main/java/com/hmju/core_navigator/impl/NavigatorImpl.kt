package com.hmju.core_navigator.impl

import android.content.Context
import android.net.Uri
import com.hmju.core_navigator.Navigator
import com.hmju.core_navigator.Router
import com.hmju.core_navigator.RouterResult
import timber.log.Timber
import javax.inject.Inject

/**
 * Description : Navigation Core Handler
 *
 * Created by juhongmin on 2025. 11. 2.
 */
internal class NavigatorImpl @Inject constructor(
    private val processors: Set<@JvmSuppressWildcards Router>
) : Navigator {

    override fun navigate(context: Context, uri: Uri): RouterResult {
	val path = uri.path ?: return RouterResult.Fail("Path is Null")
	processors.forEach { router ->
	    try {
		if (router.matches(path)) {
		    Timber.d("Router $router Path:${path}")
		    return router.execute(context, path, uri.toQueryMap())
		}
	    } catch (ex: Exception) {
		Timber.d("ERROR $ex")
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
