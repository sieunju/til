package com.hmju.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
