package com.features.base_mvvm

import android.content.Context
import android.content.Intent
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.Router
import com.hmju.core_navigator.RouterResult
import com.hmju.core.ui.base.IntentKey
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
internal class BaseMvvmRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: BaseMvvmRouter): Router
    }

    override fun route(): Route {
	return Route.BASE_MVVM
    }

    override fun matches(path: String): Boolean {
	return path.startsWith(route().path)
    }

    override suspend fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	Intent(context, RefactorBaseTestActivity::class.java).apply {
	    putExtra(IntentKey.TOKEN, "randomToken")
	    context.startActivity(this)
	}
	return RouterResult.Success()
    }
}

