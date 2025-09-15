package com.hmju.test.ui.rv_custom_paging

import androidx.test.core.app.launchActivity
import com.features.rv_custom_paging_bridge.RecyclerViewCustomPagingBridge
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
 * Created by juhongmin on 2/15/24
 */
@HiltAndroidTest
class RecyclerViewCustomPagingUiTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var bridge: RecyclerViewCustomPagingBridge

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun 커스텀_페이징_UI_테스트() {
        launchActivity<TestFragmentActivity>().apply {
            onActivity { act ->
                bridge.moveToPage(R.id.container,act.supportFragmentManager)
            }
        }
        Thread.sleep(10_000)
    }
}
