package com.til.data.interceptor

import com.hmju.loginmanager.LoginManager
import com.til.data.NetworkConfig
import com.til.data.network.AuthApiService
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * Description : Token 만료시 갱신 처리하는 클래스
 *
 * Created by juhongmin on 2022/01/12
 */
class TokenAuthenticator(
    private val loginManager: LoginManager,
    private val authApiService: AuthApiService
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // Token Expired
        return if (response.code == 401) {
            val tokenResponse = authApiService.tokenRefresh().blockingGet()
            // Token 저장
            loginManager.setToken(tokenResponse.data?.token ?: "")
            response.request.newBuilder().apply {
                header(NetworkConfig.HEADER_KEY_ACCEPT, NetworkConfig.HEADER_VAL_ACCEPT)
                header(NetworkConfig.HEADER_KEY_CONTENT, NetworkConfig.HEADER_VAL_CONTENT)
                header(NetworkConfig.HEADER_KEY_TOKEN, loginManager.getToken())
            }.build()
        } else {
            null
        }
    }
}