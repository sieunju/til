package com.features.activity_result.result_callback

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.features.activity_result.result_callback.EditTextActivity
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
internal class ActivityResultCallbackRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: ActivityResultCallbackRouter): Router
    }

    override fun route(): Route {
	return Route.ACTIVITY_RESULT_CALLBACK
    }

    override fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	return RouterResult.Fail("Activity Callback")
    }

    override fun executeForResult(
	context: Context,
	path: String,
	params: Map<String, String>,
	launcher: ActivityResultLauncher<Intent>
    ): RouterResult {
	launcher.launch(Intent(context, EditTextActivity::class.java))
	return RouterResult.Success()
    }
}