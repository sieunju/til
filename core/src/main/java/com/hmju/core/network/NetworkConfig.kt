package com.hmju.core.network

/**
 * Description : Network 설정 정의 클래스
 *
 * Created by juhongmin on 2022/01/12
 */
object NetworkConfig {
    const val CONNECT_TIME_OUT = 60_000L
    const val READ_TIME_OUT = 60_000L
    const val WRITE_TIME_OUT = 60_000L
    const val HEADER_KEY_AUTHORIZATION = "Authorization"

    object Header {
        const val KEY_ACCEPT = "accept"
        const val VAL_ACCEPT = "application/json"
        const val KEY_CONTENT = "Content-Type"
        const val VAL_CONTENT = "application/json"
        const val KEY_AUTH = "Authorization"
    }
}