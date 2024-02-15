package com.hmju.test

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.test.core.app.launchActivity
import com.features.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import timber.log.Timber

/**
 * Description :
 *
 * Created by juhongmin on 2023/07/29
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class MainUiTest {
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
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(object : Timber.DebugTree() {

            override fun createStackElementTag(element: StackTraceElement): String {
                val str = StringBuilder("J_TEST")
                try {
                    str.append(
                        element.className
                            .substringAfterLast(".")
                            .substringBefore("$")
                    )
                    str.append(":")
                    str.append(element.methodName.substringAfterLast("."))
                } catch (ex: Exception) {
                }
                return str.toString()
            }
        })
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
    fun repeatLifecycle() {
        runTest {
            launchActivity<MainActivity>().apply {
                onActivity { act ->
                    act.lifecycleScope.launch {
                        act.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                            uiState.collect {
                                Timber.d("UI_STATE $it")
                            }
                        }
                    }
                }
            }
            loopUiState()
        }

    }
}