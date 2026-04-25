package com.features.main

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
 * Created by juhongmin on 2025. 11. 9.
 */
internal class MainRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: MainRouter): Router
    }

    override fun route(): Route {
	return Route.MAIN
    }

    override fun matches(path: String): Boolean {
	return path == route().path
    }

    override suspend fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	val intent = Intent(context, MainActivity::class.java)
	intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
	intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
	context.startService(intent)
	return RouterResult.Success()
    }
}