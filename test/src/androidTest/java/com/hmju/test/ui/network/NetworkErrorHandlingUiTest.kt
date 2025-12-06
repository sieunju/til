package com.hmju.test.ui.network

import androidx.test.core.app.launchActivity
import com.hmju.core_navigator.Navigator
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.RouteParamsKey
import com.hmju.core_navigator.addQuery
import com.hmju.test.R
import com.hmju.test.TestFragmentActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2/25/24
 */
@HiltAndroidTest
class NetworkErrorHandlingUiTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var navigator: Navigator

    @Before
    fun init() {
	hiltRule.inject()
    }

    @Test
    fun 네트워크_에러_헨들링_UI_테스트() {
	launchActivity<TestFragmentActivity>().apply {
	    onActivity { act ->
		navigator.navigate(act, Route.NETWORK_ERROR_HANDLING.getUri {
		    addQuery(RouteParamsKey.LAYOUT_ID, R.id.container)
		    addQuery(RouteParamsKey.IS_INTERNAL, true)
		})
	    }
	}
	Thread.sleep(10_000)
    }
}