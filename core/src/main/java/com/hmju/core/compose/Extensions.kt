package com.hmju.core.compose

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.hmju.core.compose.ComposeLifecycleState.Companion.from


/**
 * Compose 전용 Lifecycle Update 처리하는 함수
 * @param lifecycleOwner Android LifecycleOwner
 */
@Composable
fun rememberLifecycleUpdatedState(
    lifecycleOwner: LifecycleOwner
): MutableState<ComposeLifecycleState> {
    val currentState = remember(lifecycleOwner) { mutableStateOf(ComposeLifecycleState.UN_KNOWN) }
    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            val newState = event.from()
            if (currentState.value != newState &&
                newState != ComposeLifecycleState.UN_KNOWN
            ) {
                currentState.value = newState
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    return currentState
}

/**
 * 다른데 터치할때 키보드 내리기 위한 유틸 함수
 * @param focusManager LocalFocusManager
 */
fun Modifier.addFocusCleaner(
    focusManager: FocusManager,
    doOnClear: () -> Unit = {}
): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}
