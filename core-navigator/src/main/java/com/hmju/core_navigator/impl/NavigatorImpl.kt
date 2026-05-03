package com.hmju.core_navigator.impl

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
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

    override fun navigate(context: Context, uri: Uri, launcher: ActivityResultLauncher<Intent>?) {
	val path = uri.path ?: return
	Timber.d("Navigation $uri")
	for (router in processors) {
	    try {
		if (router.matches(path)) {
		    val result = if (launcher != null) {
			router.executeForResult(context, path, uri.toQueryMap(), launcher)
		    } else {
			router.execute(context, path, uri.toQueryMap())
		    }
		    if (result is RouterResult.Success) return
		    Timber.d("Router Fail Result $result")
		}
	    } catch (ex: Exception) {
		Timber.e("Error $ex")
		return
	    }
	}
    }

    private fun Uri.toQueryMap(): Map<String, String> {
	return try {
	    queryParameterNames
		.filterNot { it.isNullOrBlank() }
		.associateWith { key -> getQueryParameter(key).orEmpty() }
	} catch (_: Exception) {
	    emptyMap()
	}
    }
}
