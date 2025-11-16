package com.features.network_error_handling

import android.content.Context
import androidx.fragment.app.commit
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.RouteParamsKey
import com.hmju.core_navigator.Router
import com.hmju.core_navigator.RouterResult
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2025. 11. 16.
 */
internal class NetworkErrorHandlingRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: NetworkErrorHandlingRouter): Router
    }

    override fun route(): Route {
	return Route.NETWORK_ERROR_HANDLING
    }

    override fun matches(path: String): Boolean {
	return path.startsWith(route().path)
    }

    override fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	val activity = getFragmentActivity(context)
	    ?: return RouterResult.Fail("Context not FragmentActivity")
	Timber.d("Context? $activity")
	val layoutId = params.get(RouteParamsKey.LAYOUT_ID)
	    ?.toIntOrNull()
	    ?.takeIf { isValidIdRes(context, it) }
	    ?: return RouterResult.Fail("Invalidate LayoutId ")
	val fm = activity.supportFragmentManager
	fm.commit {
	    replace(layoutId, ErrorHandlingFragment())
	}
	return RouterResult.Success()
    }
}