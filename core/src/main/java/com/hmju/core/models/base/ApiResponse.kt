package com.hmju.core.models.base

import com.hmju.core.models.error.JSendException

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
     * @param err Error
     */
    data class Fail(val err: JSendException) : ApiResponse<Nothing>()
}
