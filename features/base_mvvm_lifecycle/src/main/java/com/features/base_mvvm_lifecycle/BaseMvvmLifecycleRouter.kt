package com.features.base_mvvm_lifecycle

import android.content.Context
import android.content.Intent
import com.features.base_mvvm_lifecycle.ui.test_1.MvvmLifecycleTestActivity
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
internal class BaseMvvmLifecycleRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: BaseMvvmLifecycleRouter): Router
    }

    override fun route(): Route {
	return Route.BASE_MVVM_LIFECYCLE
    }

    override fun matches(path: String): Boolean {
	return path.startsWith(route().path)
    }

    override suspend fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	Intent(context, MvvmLifecycleTestActivity::class.java).apply {
	    // SerializableEntity는 params에서 복원할 수 없으므로 기본 동작만 수행
	    context.startActivity(this)
	}
	return RouterResult.Success()
    }
}

