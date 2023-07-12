package com.hmju.test.coroutine

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Description :
 * https://developer.android.com/kotlin/flow/stateflow-and-sharedflow?hl=ko#sharedflow
 * Created by juhongmin on 2023/03/05
 */


@HiltAndroidTest
class ShareFlowStudy {

    companion object {
        @JvmStatic
        val tickFlow = MutableSharedFlow<String>()
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    private suspend fun loopTick() {
        return withContext(Dispatchers.Default) {
            for (idx in 0 until 10) {
                if (idx % 3 == 0) {
                    tickFlow.emit("TICK_${idx}")
                } else if (idx % 3 == 1) {
                    tickFlow.emit("TICK_${idx}")
                } else {
                    tickFlow.emit("TICK_${idx}")
                }
                // println("UiState TEST ${uiState.value}")
                delay(1000)
            }
        }
    }

    private suspend fun loopTickV2() {
        return withContext(Dispatchers.Default) {
            for (idx in 0 until 10) {
                if (idx % 3 == 0) {
                    tickFlow.emit("TOCK_${idx}")
                } else if (idx % 3 == 1) {
                    tickFlow.emit("TOCK_${idx}")
                } else {
                    tickFlow.emit("TOCK_${idx}")
                }
                // println("UiState TEST ${uiState.value}")
                delay(333)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun shareFlow() {
        runTest {
            backgroundScope.launch {
                // Rx HotObservable RxEventBus 같은 기능
                tickFlow.collect {
                    println(it)
                }
            }
            val backV1 = backgroundScope.async { loopTick() }
            val backV2 = backgroundScope.async { loopTickV2() }
            backV1.await()
            backV2.await()

        }
    }
}
