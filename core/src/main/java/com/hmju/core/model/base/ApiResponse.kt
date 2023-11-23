package com.hmju.core.model.base

/**
 * Description : Coroutines Network Base Response
 *
 * Created by juhongmin on 2023/02/04
 */
sealed class ApiResponse<out T> {

    /**
     * Network Success Response Data
     */
    data class Success<out T>(val data: T) : ApiResponse<T>()

    data class Fail(val err: Throwable) : ApiResponse<Nothing>()
}
