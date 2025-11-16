package com.hmju.core_navigator

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.internal.managers.ViewComponentManager

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
    abstract fun execute(context: Context, path: String, params: Map<String, String>): RouterResult
    protected fun isValidIdRes(context: Context, id: Int): Boolean {
	return try {
	    context.resources.getResourceName(id)
	    true
	} catch (e: Resources.NotFoundException) {
	    false
	}
    }

    protected fun getFragmentActivity(context: Context): FragmentActivity? {
	if (context is FragmentActivity) return context
	if (context is ViewComponentManager.FragmentContextWrapper) {
	    // Hilt Fragment or ViewHolder Case..
	    var tmpContext = context
	    while (tmpContext is ContextWrapper &&
		tmpContext !is FragmentActivity
	    ) {
		tmpContext = tmpContext.baseContext as ContextWrapper
	    }

	    if (tmpContext is FragmentActivity) {
		return tmpContext
	    }
	} else if (context is ContextWrapper) {
	    var tmpContext = context
	    while (tmpContext is ContextWrapper) {
		if (tmpContext is FragmentActivity) {
		    return tmpContext
		}
		tmpContext = tmpContext.baseContext
	    }
	}
	return null
    }
}
