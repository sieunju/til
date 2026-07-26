package com.features.fragment_navigation

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
 * Created by juhongmin on 2026. 7. 26.
 */
internal class FragmentNavigationRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: FragmentNavigationRouter): Router
    }

    override fun route(): Route {
	return Route.FRAGMENT_NAVIGATION
    }

    override fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	context.startActivity(Intent(context, RootActivity::class.java))
	return RouterResult.Success()
    }
}
