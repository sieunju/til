package com.hmju.core.network.interceptor

import com.hmju.core.login_manager.LoginManager
import com.hmju.core.network.NetworkConfig
import com.hmju.core.pref.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Description : Header Interceptor
 *
 * Created by juhongmin on 2022/01/12
 */
class HeaderInterceptor(
    private val loginManager: LoginManager,
    private val prefManager: PreferenceManager
) : Interceptor {

    private val simpleFormat: SimpleDateFormat by lazy {
        SimpleDateFormat("yyyy. MM. dd. HH:mm:ss", Locale.KOREAN)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder()
            .header(NetworkConfig.HEADER_KEY_ACCEPT, NetworkConfig.HEADER_VAL_ACCEPT)
            .header(NetworkConfig.HEADER_KEY_CONTENT, NetworkConfig.HEADER_VAL_CONTENT)
            // .header(NetworkConfig.HEADER_KEY_TOKEN, loginManager.getToken())
            .header("Authorization", prefManager.getString(PreferenceManager.KEY_TOKEN))
            .header(
                "ExpiredToken",
                prefManager.getLong(PreferenceManager.KEY_TOKEN_EXPIRED_MS).toString()
            )
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
        val expiredTime = req.header("ExpiredToken")?.toLong()
        str.appendLine("Code ${response.code} Request ${response.request.url} (${tookMs}ms)")
        str.appendLine("Authorization: ${req.header("Authorization")}")
        if (expiredTime != null) {
            str.appendLine("ExpiredToken: ${simpleFormat.format(Date(expiredTime))}")
        }

        Timber.tag("HTTP_LOG").d(str.toString())
        return response
    }
}
