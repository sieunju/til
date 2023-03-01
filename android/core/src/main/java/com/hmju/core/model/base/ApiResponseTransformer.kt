package com.hmju.core.model.base

inline fun <T> ApiResponse<T>.onSuccess(
    crossinline callback: ApiResponse.Success<T>.() -> Unit
): ApiResponse<T> {
    if (this is ApiResponse.Success) {
        callback(this)
    }
    return this
}

inline fun <T> ApiResponse<T>.onError(
    crossinline callback: ApiResponse.Fail.() -> Unit
): ApiResponse<T> {
    if (this is ApiResponse.Fail) {
        callback(this)
    }
    return this
}
