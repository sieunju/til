package com.hmju.test.ui.network

import androidx.test.core.app.launchActivity
import com.features.network_error_handling_bridge.NetworkErrorHandlingBridge
import com.hmju.test.TestFragmentActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import com.hmju.test.R

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
    lateinit var bridge: NetworkErrorHandlingBridge

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun 네트워크_에러_헨들링_UI_테스트(){
        launchActivity<TestFragmentActivity>().apply {
            onActivity { act ->
                bridge.moveToPage(R.id.container,act.supportFragmentManager)
            }
        }
        Thread.sleep(10_000)
    }
}