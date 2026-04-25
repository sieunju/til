package com.features.recyclerview

import android.content.Context
import android.content.Intent
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.RouteParamsKey
import com.hmju.core_navigator.Router
import com.hmju.core_navigator.RouterResult
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2025. 11. 12.
 */
internal class RecyclerViewRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: RecyclerViewRouter): Router
    }

    override fun route(): Route {
	return Route.RECYCLERVIEW
    }

    override fun matches(path: String): Boolean {
	return path.startsWith(route().path)
    }

    override suspend fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	Intent(context, RecyclerViewActivity::class.java).apply {
	    putExtra(RouteParamsKey.PATH, path)
	    putExtra(
		RouteParamsKey.IS_INTERNAL,
		params[RouteParamsKey.IS_INTERNAL]
	    )
	    context.startActivity(this)
	}
	return RouterResult.Success()
    }
}

