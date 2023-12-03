package com.hmju.core.network.interceptor

import com.hmju.core.login_manager.LoginManager
import com.hmju.core.network.NetworkConfig
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.util.concurrent.TimeUnit

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

        val startNs = System.nanoTime()
        val response: Response
        try {
            response = chain.proceed(req)
        } catch (e: Exception) {
            throw e
        }
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        val str = StringBuilder()
        str.appendLine("Code ${response.code} Request ${response.request.url.encodedPath} (${tookMs}ms)")
        str.appendLine("Authorization: ${req.header("Authorization")}")
        Timber.tag("Network_Test").d(str.toString())
        return response
    }
}
