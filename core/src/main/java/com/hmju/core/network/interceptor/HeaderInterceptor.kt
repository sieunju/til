package com.hmju.core.network.interceptor

import com.hmju.core.login_manager.LoginManager
import com.hmju.core.network.NetworkConfig
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
        val req = chain.request().newBuilder()
            .header(NetworkConfig.HEADER_KEY_ACCEPT, NetworkConfig.HEADER_VAL_ACCEPT)
            .header(NetworkConfig.HEADER_KEY_CONTENT, NetworkConfig.HEADER_VAL_CONTENT)
            .header(NetworkConfig.HEADER_KEY_AUTHORIZATION, loginManager.getToken())
            .build()
        return chain.proceed(req)
    }
}
