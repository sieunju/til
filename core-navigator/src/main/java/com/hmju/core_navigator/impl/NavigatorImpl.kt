package com.hmju.core_navigator.impl

import android.content.Context
import android.net.Uri
import com.hmju.core_navigator.Navigator
import com.hmju.core_navigator.Router
import com.hmju.core_navigator.RouterResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    @OptIn(DelicateCoroutinesApi::class)
    override fun navigate(context: Context, uri: Uri) {
        val path = uri.path ?: return
        Timber.d("Navigation $uri")
        GlobalScope.launch(Dispatchers.IO) {
            for (router in processors) {
                try {
                    if (router.matches(path)) {
                        val result = withContext(Dispatchers.Main) {
                            router.execute(context, path, uri.toQueryMap())
                        }
                        if (result is RouterResult.Success) return@launch
                        Timber.d("Router Fail Result $result")
                    }
                } catch (ex: Exception) {
                    Timber.e("Error $ex")
                    return@launch
                }
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
