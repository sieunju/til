package com.features.rv_diff_util_performance

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
 * Created by juhongmin on 2025. 12. 6.
 */
internal class RecyclerViewDiffPerformanceRouter @Inject constructor() : Router() {

    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindModule {
	@Binds
	@IntoSet
	fun bind(impl: RecyclerViewDiffPerformanceRouter): Router
    }

    override fun route(): Route {
	return Route.RECYCLERVIEW_DIFF_UTIL_PERFORMANCE
    }

    override suspend fun execute(
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
	    replace(layoutId, DiffUtilPerformanceFragment())
	    addToBackStack("DiffUtilPerformanceFragment")
	}
	return RouterResult.Success()
    }
}