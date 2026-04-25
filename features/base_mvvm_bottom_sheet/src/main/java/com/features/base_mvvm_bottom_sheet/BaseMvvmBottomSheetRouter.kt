package com.features.base_mvvm_bottom_sheet

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.features.base_mvvm_bottom_sheet.ui.RefactorBottomSheetDialog
import com.features.base_mvvm_bottom_sheet.ui.RefactorSharedBottomSheetDialog
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
 * Created by juhongmin on 2025. 11. 16.
 */
internal class BaseMvvmBottomSheetRouter @Inject constructor() : Router() {
    @Module
    @InstallIn(SingletonComponent::class)
    internal interface BindingModule {
	@Binds
	@IntoSet
	fun bind(impl: BaseMvvmBottomSheetRouter): Router
    }

    override fun route(): Route {
	return Route.BASE_MVVM_BOTTOM_SHEET
    }

    override fun matches(path: String): Boolean {
	return path.startsWith(route().path)
    }

    override suspend fun execute(
	context: Context,
	path: String,
	params: Map<String, String>
    ): RouterResult {
	if (context !is FragmentActivity) return RouterResult.Fail("Context not FragmentActivity")
	val fm = context.supportFragmentManager
	if (params[RouteParamsKey.TARGET] == "share") {
	    RefactorBottomSheetDialog()
		.simpleShow(fm, "RefactorBottomSheetDialog")
	} else {
	    RefactorSharedBottomSheetDialog()
		.simpleShow(fm, "RefactorSharedBottomSheetDialog")
	}
	return RouterResult.Success()
    }
}