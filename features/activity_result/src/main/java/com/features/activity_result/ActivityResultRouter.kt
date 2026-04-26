package com.features.activity_result

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
 * Created by juhongmin on 2026. 4. 26.
 */
internal class ActivityResultRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: ActivityResultRouter): Router
    }

    override fun route(): Route {
	return Route.ACTIVITY_RESULT
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