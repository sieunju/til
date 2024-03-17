package com.hmju.core.network.interceptor

import com.hmju.core.login_manager.LoginManager
import com.hmju.core.models.auth.AuthTokenEntity
import com.hmju.core.network.AuthManager
import com.hmju.core.network.NetworkConfig
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.math.pow

/**
 * Description : Token 만료시 갱신 처리하는 클래스
 *
 * Created by juhongmin on 2022/01/12
 */
internal class TokenAuthenticator(
    private val loginManager: LoginManager,
    private val authManager: AuthManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // Token Expired
        Timber.d("TokenAuthenticator ${response.code}")
        return if (response.code == 401) {
            handleTokenRefresh()
            val req = response.request.newBuilder()
                .header(NetworkConfig.HEADER_KEY_AUTHORIZATION, loginManager.getToken())
                .build()
            Timber.tag("Network_Test").e("Authenticator ${req.url.encodedPath}")
            req
        } else {
            null
        }
    }

    @Synchronized
    private fun handleTokenRefresh() {
        if (authManager.isRefreshToken()) {
            val entity = reqRetryRefreshToken().blockingGet()
            loginManager.setToken(entity.token)
            loginManager.setRefreshToken(entity.refreshToken)
        } else {
            try {
                val entity = authManager.createToken()
                loginManager.setToken(entity.token)
                loginManager.setRefreshToken(entity.refreshToken)
            } catch (ex: Exception) {
                Timber.d("CreateToken Error $ex")
            }
        }
    }

    /**
     * 토큰 갱신 API 실패시 재시도 하는 함수
     */
    private fun reqRetryRefreshToken(): Single<AuthTokenEntity> {
        return Single.create { emitter ->
            try {
                val res = authManager.refreshToken()
                emitter.onSuccess(res)
            } catch (ex: Exception) {
                emitter.onError(ex)
            }
        }.compose(applyRetryPolicy()).subscribeOn(Schedulers.io())
    }

    /**
     * 재시도 N 번 처리하는 함수
     */
    private fun <T : Any> applyRetryPolicy(
        maxRetries: Int = 3
    ) = SingleTransformer<T, T> { single ->
        single.retryWhen { errors ->
            errors.zipWith(
                Flowable.range(1, maxRetries + 1)
            ) { error, retryCount -> Pair(error, retryCount) }
                .flatMap { (err, retryCount) ->
                    // 최대 maxRetries번까지 재시도
                    if (retryCount <= maxRetries) {
                        // 지수 백오프 정책을 적용하여 재시도 간격을 증가시킴
                        val delayMillis = 2.0.pow(retryCount.toDouble()).toLong() * 1000
                        Flowable.timer(delayMillis, TimeUnit.MILLISECONDS)
                    } else {
                        // 재시도 횟수 초과 또는 에러가 기타 경우에는 에러 전파
                        Flowable.error(err)
                    }
                }
        }
    }
}
