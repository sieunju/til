package com.features.rv_simple_like

import android.content.Context
import androidx.fragment.app.commit
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
 * Created by juhongmin on 2025. 12. 2.
 */
internal class RecyclerSimpleLikeRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: RecyclerSimpleLikeRouter): Router
    }

    override fun route(): Route {
	return Route.RECYCLERVIEW_SIMPLE_LIKE
    }

    override fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	if (params[RouteParamsKey.IS_INTERNAL]?.toBoolean() == false) {
	    return RouterResult.Fail("DeeplinkType")
	}
	val activity = getFragmentActivity(context)
	    ?: return RouterResult.Fail("Context not FragmentActivity")
	val layoutId = getFragmentLayoutId(
	    context,
	    params
	) ?: return RouterResult.Fail("Invalidate LayoutId ")
	val fm = activity.supportFragmentManager
	fm.commit {
	    replace(layoutId, SimpleLikeFragment())
	    addToBackStack("SimpleLikeFragment")
	}
	return RouterResult.Success()
    }
}
