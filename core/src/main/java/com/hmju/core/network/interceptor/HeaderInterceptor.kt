package com.hmju.core.network.interceptor

import com.hmju.core.login_manager.LoginManager
import com.hmju.core.network.NetworkConfig
import com.hmju.core.pref.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Description : Header Interceptor
 *
 * Created by juhongmin on 2022/01/12
 */
class HeaderInterceptor(
    private val loginManager: LoginManager,
    private val prefManager: PreferenceManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        return chain.proceed(origin.newBuilder().apply {
            header(NetworkConfig.HEADER_KEY_ACCEPT, NetworkConfig.HEADER_VAL_ACCEPT)
            header(NetworkConfig.HEADER_KEY_CONTENT, NetworkConfig.HEADER_VAL_CONTENT)
            // header(NetworkConfig.HEADER_KEY_TOKEN, loginManager.getToken())
            header(
                "ExpiredToken",
                prefManager.getLong(PreferenceManager.KEY_TOKEN_EXPIRED_MS).toString()
            )
        }.build())
    }
}
