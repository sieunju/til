package com.features.network

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hmju.domain.usecase.GetGoodsUseCase
import com.til.model.params.GoodsParamMap
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

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

    @Inject
    lateinit var getGoodsUseCase: GetGoodsUseCase

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun testNetworkStart(){
        println("네트워크 TIL Start")
        getGoodsUseCase(GoodsParamMap())
            .subscribe({
                println("SUCC ${it.size}")
            },{
                println("ERROR ${it}")
            })
//        launchActivity<NetworkActivity>().apply {
//            moveToState(Lifecycle.State.RESUMED)
//        }
        println("네트워크 TIL End")
        Thread.sleep(3_000)
    }
}