package com.hmju.core.model.base

import retrofit2.Response

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

    /**
     * Network Fail
     */
    data class Fail<T>(val response: Response<T>) : ApiResponse<Nothing>()

    /**
     * Exception
     */
    data class Error(val ex: Throwable) : ApiResponse<Nothing>()
}
