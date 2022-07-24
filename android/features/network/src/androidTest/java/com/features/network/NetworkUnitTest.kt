package com.features.network

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Description :
 *
 * Created by juhongmin on 2022/07/24
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class NetworkUnitTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun 네트워크_TIL(){
        launchActivity<NetworkActivity>().apply {
            moveToState(Lifecycle.State.CREATED)
        }
        Thread.sleep(3000)
    }
}