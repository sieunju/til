package com.hmju.core.compose

import androidx.lifecycle.Lifecycle

/**
 * Description : 필요한 Lifecycle 만 중복 없이 처리할수 있도록 정의한 클래스
 *
 * Created by juhongmin on 4/5/24
 */
enum class ComposeLifecycleState {
    UN_KNOWN,
    ON_CREATE,
    ON_RESUME,
    ON_STOP;

    companion object {
        fun Lifecycle.Event.from(): ComposeLifecycleState {
            return when (this) {
                Lifecycle.Event.ON_CREATE -> ON_CREATE
                Lifecycle.Event.ON_RESUME -> ON_RESUME
                Lifecycle.Event.ON_STOP -> ON_STOP
                else -> UN_KNOWN
            }
        }
    }
}
