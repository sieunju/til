package com.hmju.test

import com.features.recyclerview.usecase.GetGoodsUseCase
import com.hmju.core.data.model.params.GoodsParamMap
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/07/24
 */
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