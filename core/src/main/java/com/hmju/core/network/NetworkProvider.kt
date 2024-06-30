package com.hmju.core.network

/**
 * Description : 네트워크 통신할수 있는 제공자
 *
 * Created by juhongmin on 2023/03/27
 */
interface NetworkProvider {

    fun <T> createApiService(service: Class<T>): T
}
