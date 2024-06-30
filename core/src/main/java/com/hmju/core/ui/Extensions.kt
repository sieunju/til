package com.hmju.core.ui

import android.content.ContextWrapper
import android.view.View
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.internal.managers.ViewComponentManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform

/**
 * FragmentActivity 가져오는 View 기반 확장 함수
 * @return FragmentActivity Nullable
 */
fun View.getFragmentActivity(): FragmentActivity? {
    if (context is FragmentActivity) {
        return context as FragmentActivity
    } else if (context is ViewComponentManager.FragmentContextWrapper) {
        // Hilt Fragment or ViewHolder Case..
        var tmpContext = this.context
        while (tmpContext is ContextWrapper &&
            tmpContext !is FragmentActivity
        ) {
            tmpContext = tmpContext.baseContext as ContextWrapper
        }

        if (tmpContext is FragmentActivity) {
            return tmpContext
        }
    } else if (context is ContextWrapper) {
        var tmpContext = this.context
        while (tmpContext is ContextWrapper) {
            if (tmpContext is FragmentActivity) {
                return tmpContext
            }
            tmpContext = tmpContext.baseContext
        }
    }
    return null
}

/**
 * StateFlow Observer 처리하는 함수
 * [SharingStarted.WhileSubscribed] 정책
 * stopTimeoutMillis Collector 가 모두 사라진 이후 정치할 Delay
 * replayExpirationMillis 캐싱할 값을 유지할 시간
 * ex.)
 * private val _uiStateFlow = MutableStateFlow<UiState>()
 * val successMessage: StateFlow<String> = _uiStateFlow
 *         .observer({ if (it is UiState.Success) it.value else "none" }, "default")
 *
 * @param transform Map 기능을 하는 함수 [Transformations.map] 동일
 * @param initValue NotNull 초기 값
 */
inline fun <reified T, I : Any> Flow<I>.observer(
    crossinline transform: suspend (value: I) -> T,
    initValue: T,
    scope: CoroutineScope
): StateFlow<T> {
    return transform { value ->
        return@transform emit(transform(value))
    }.stateIn(
        scope,
        SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 0,
            replayExpirationMillis = 3000
        ),
        initValue
    )
}

/**
 * StateFlow Simple 함수
 */
inline fun <reified T> Flow<T>.stateIn(
    initValue: T,
    scope: CoroutineScope
): StateFlow<T> {
    return stateIn(
        scope,
        SharingStarted.WhileSubscribed(
            stopTimeoutMillis = 0,
            replayExpirationMillis = 3000
        ),
        initValue
    )
}