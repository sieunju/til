package com.hmju.test.coroutine

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Description :
 * https://developer.android.com/kotlin/flow/stateflow-and-sharedflow?hl=ko#sharedflow
 * Created by juhongmin on 2023/03/05
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class StateFlowStudy {

    sealed class UiState {
        object UnKnown : UiState()
        object Loading : UiState()
        data class Success(val id: Long) : UiState()
        data class Fail(val msg: String) : UiState()
    }

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val _uiState = MutableStateFlow<UiState>(UiState.UnKnown)
    val uiState: StateFlow<UiState> get() = _uiState

    @Before
    fun init() {
        hiltRule.inject()
    }

    private suspend fun loopUiState() {
        return withContext(Dispatchers.Default) {
            for (idx in 0 until 10) {
                if (idx % 3 == 0) {
                    _uiState.value = UiState.Loading
                } else if (idx % 3 == 1) {
                    _uiState.value = UiState.Success(idx.toLong())
                } else {
                    _uiState.value = UiState.Fail("Error $idx")
                }
                // println("UiState TEST ${uiState.value}")
                delay(1000)
            }
        }
    }

    @Test
    fun stateFlowTest() {
        runTest {
            // StateFlow 은 단순 sealed 로 어떤 행위에 대해서 값을 계속해서 처리하는 클래스
            // StateFlow 는 LiveData 처럼 관찰 가능한 데이터 홀더 클래스이지만
            // 초기 상태를 전달해줘야 함 Custom하게 사용하는 NonNullLiveData 와 같은 맥락
            // LiveData 는 lifecycle 의존하여 STOPPED 되면 자동으로 취소하지만, StateFlow 자동으로 중지 하지 않음
            // 동일하게 하려면 androidx.lifecycle 2.4.0 부터 가능한 Lifecycle.repeatOnLifecycle 을 사용해야함
            // MVVM + MVI 패턴에서 사용하기 적합할거 같다.
            backgroundScope.launch(UnconfinedTestDispatcher()) {
                uiState.collect {
                    println("UiState $it")
                }
            }
            loopUiState()
        }
    }
}
