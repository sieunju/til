package com.hmju.core.model.base

/**
 * Description : Coroutines Network Base Response
 *
 * Created by juhongmin on 2023/02/04
 */
sealed class ApiResponse<out T> {

    enum class FailType {
        NETWORK, HTTP, UN_KNOWN
    }

    /**
     * Network Success Response Data
     */
    data class Success<out T>(val data: T) : ApiResponse<T>()

    data class Fail(
        val err: Throwable
    ) : ApiResponse<Nothing>()

//    /**
//     * Network Fail Data Model
//     * @see FailType.HTTP HTTP Error
//     * @see FailType.NETWORK Network Config Error
//     * @see FailType.UN_KNOWN UN KNOWN
//     *
//     * @param type FailType
//     * @param msg Error Message
//     * @param errBody when [FailType.HTTP] Error Body
//     */
//    data class Fail(
//        val type: FailType,
//        val msg: CharSequence,
//        val err: Throwable? = null,
//        val errBody: ResponseBody? = null
//    ) : ApiResponse<Nothing>()
}
