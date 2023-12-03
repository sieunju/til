package com.hmju.test

import com.features.recyclerview.usecase.GetGoodsUseCase
import com.hmju.core.model.params.GoodsParameter
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
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testNetworkStart() {
        val result = getGoodsUseCase(GoodsParameter())
            .flatMap { getGoodsUseCase(GoodsParameter()) }
            .blockingGet()
        println("네트워크 TEST ${result.size}")
    }
}