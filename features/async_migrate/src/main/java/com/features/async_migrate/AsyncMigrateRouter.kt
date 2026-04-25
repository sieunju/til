package com.features.async_migrate

import android.content.Context
import android.content.Intent
import com.hmju.core_navigator.Route
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
internal class AsyncMigrateRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: AsyncMigrateRouter): Router
    }

    override fun route(): Route {
	return Route.ASYNC_MIGRATE
    }

    override fun matches(path: String): Boolean {
	return path.startsWith(route().path)
    }

    override fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	Intent(context, AsyncMigrateActivity::class.java).apply {
	    context.startActivity(this)
	}
	return RouterResult.Success()
    }
}