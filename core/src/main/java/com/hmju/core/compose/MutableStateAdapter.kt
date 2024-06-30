package com.hmju.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Description :
 *
 * Created by juhongmin on 4/7/24
 */
class MutableStateAdapter<T>(
    private val state: State<T>,
    private val mutate: (T) -> Unit
) : MutableState<T> {

    override var value: T
        get() = state.value
        set(value) {
            mutate(value)
        }

    override fun component1(): T = value
    override fun component2(): (T) -> Unit = { value = it }
}

@Composable
fun <T> MutableStateFlow<T>.collectAsMutableState(
    context: CoroutineContext = EmptyCoroutineContext
): MutableState<T> = MutableStateAdapter(
    state = collectAsState(context),
    mutate = { value = it }
)
