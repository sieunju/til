package com.hmju.core.model.base

/**
 * References Kotlin Result onSuccess
 */
inline fun <T> ApiResponse<T>.onSuccess(
    crossinline callback: ApiResponse.Success<T>.() -> Unit
): ApiResponse<T> {
    if (this is ApiResponse.Success) {
        callback(this)
    }
    return this
}

/**
 * References Kotlin Result onFailure
 */
inline fun <T> ApiResponse<T>.onError(
    crossinline callback: ApiResponse.Fail.() -> Unit
): ApiResponse<T> {
    if (this is ApiResponse.Fail) {
        callback(this)
    }
    return this
}

/**
 * ApiResponse Get Nullable
 */
inline fun <reified T> ApiResponse<T>.getOrNull(): T? {
    return if (this is ApiResponse.Success) {
        data
    } else {
        null
    }
}

/**
 * ApiResponse Get NonNull
 */
inline fun <reified T> ApiResponse<T>.getOrDefault(defValue: T): T {
    return if (this is ApiResponse.Success) {
        data
    } else {
        defValue
    }
}
