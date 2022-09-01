package com.hmju.network.interceptor

import com.hmju.core.login_manager.LoginManager
import com.hmju.network.NetworkConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Description : Header Interceptor
 *
 * Created by juhongmin on 2022/01/12
 */
class HeaderInterceptor(
    private val loginManager: LoginManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        return chain.proceed(origin.newBuilder().apply {
            header(NetworkConfig.HEADER_KEY_ACCEPT, NetworkConfig.HEADER_VAL_ACCEPT)
            header(NetworkConfig.HEADER_KEY_CONTENT, NetworkConfig.HEADER_VAL_CONTENT)
            header(NetworkConfig.HEADER_KEY_TOKEN, loginManager.getToken())
        }.build())
    }
}
