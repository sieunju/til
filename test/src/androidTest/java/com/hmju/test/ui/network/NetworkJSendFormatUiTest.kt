package com.hmju.test.ui.network

import androidx.test.core.app.launchActivity
import com.features.network_jsend_format_bridge.NetworkJSendFormatBridge
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
 * Created by juhongmin on 2/26/24
 */
@HiltAndroidTest
class NetworkJSendFormatUiTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var bridge: NetworkJSendFormatBridge

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun JSEND_포멧_UI_테스트(){
        launchActivity<TestFragmentActivity>().apply {
            onActivity { act->
                bridge.moveToPage(R.id.container,act.supportFragmentManager)
            }
        }
        Thread.sleep(10_000)
    }
}