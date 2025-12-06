package com.features.room_observer

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
internal class RoomObserverRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: RoomObserverRouter): Router
    }

    override fun route(): Route {
	return Route.ROOM_OBSERVER
    }

    override fun matches(path: String): Boolean {
	return path.startsWith(route().path)
    }

    override fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	Intent(context, RoomObserverActivity::class.java).apply {
	    context.startActivity(this)
	}
	return RouterResult.Success()
    }
}

