package com.hmju.core.data.interceptor

import com.hmju.core.data.NetworkConfig
import com.hmju.core.data.repository.RefreshTokenRepository
import com.hmju.core.login_manager.LoginManager
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber

/**
 * Description : Token 만료시 갱신 처리하는 클래스
 *
 * Created by juhongmin on 2022/01/12
 */
class TokenAuthenticator(
    private val loginManager: LoginManager,
    private val repository: RefreshTokenRepository
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // Token Expired
        Timber.d("TokenAuthenticator ${response.code}")
        return if (response.code == 401) {
            val tokenResponse = repository.fetch().blockingGet()
            // Token 저장
            Timber.d("Refresh Token ${tokenResponse.payload}")
            try {
                loginManager.setToken(tokenResponse.payload.token)
            } catch (ex: NullPointerException) {
                loginManager.setToken("")
            }
            response.request.newBuilder().apply {
                header(NetworkConfig.HEADER_KEY_ACCEPT, NetworkConfig.HEADER_VAL_ACCEPT)
                header(NetworkConfig.HEADER_KEY_CONTENT, NetworkConfig.HEADER_VAL_CONTENT)
                header(NetworkConfig.HEADER_KEY_TOKEN, loginManager.getToken())
            }.build()
        } else {
            Timber.d("TokenAuthenticator Code ${response.code}")
            null
        }
    }
}
