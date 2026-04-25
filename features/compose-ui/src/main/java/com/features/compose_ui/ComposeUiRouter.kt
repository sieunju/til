package com.features.compose_ui

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
internal class ComposeUiRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: ComposeUiRouter): Router
    }

    override fun route(): Route {
	return Route.COMPOSE_UI
    }

    override fun matches(path: String): Boolean {
	return path.startsWith(route().path)
    }

    override suspend fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	// QueryParameter의 type에 따라 다른 Activity 시작
	val targetActivity = if (params[RouteParamsKey.TYPE] == "memo") {
	    MemoComposeUiActivity::class.java
	} else {
	    ComposeUiActivity::class.java
	}

	Intent(context, targetActivity).apply {
	    context.startActivity(this)
	}
	return RouterResult.Success()
    }
}

