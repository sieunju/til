package com.hmju.core.network.interceptor

import com.hmju.core.login_manager.LoginManager
import com.hmju.core.network.NetworkConfig
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.Route
import org.json.JSONObject
import timber.log.Timber
import java.util.concurrent.TimeUnit
import kotlin.math.pow

/**
 * Description : Token 만료시 갱신 처리하는 클래스
 *
 * Created by juhongmin on 2022/01/12
 */
class TokenAuthenticator(
    private val loginManager: LoginManager,
    private val httpClient: OkHttpClient
) : Authenticator {

    private val json: Json by lazy {
        Json {
            isLenient = true // Json 큰따옴표 느슨하게 체크.
            ignoreUnknownKeys = true // Field 값이 없는 경우 무시
            coerceInputValues = true // "null" 이 들어간경우 default Argument 값으로 대체
        }
    }

    private val TIME_DELAY = 0

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
        // val refreshToken = reqRefreshToken()
        val refreshToken = reqRetryRefreshToken().blockingGet()
        loginManager.setToken(refreshToken)
    }

    /**
     * Request API Refresh Token
     *
     * @return 재발급 받은 토큰 데이터 모델
     */
    @OptIn(ExperimentalSerializationApi::class)
    private fun reqRefreshToken(): String {
        val reqBody = JSONObject()
        reqBody.put("email", "j.sieun@gmail.com")
        reqBody.put("delay", TIME_DELAY)
        reqBody.put("expiredTime", "1m")
        val req = Request.Builder()
            .url(NetworkConfig.BASE_URL.plus("/api/til/auth/refresh"))
            .post(reqBody.toString().toRequestBody())
            .build()

        /**
         * {
         *   "status": true,
         *   "data": {
         *     "payload": {
         *       "token": "JWT Token Example..."
         *     }
         *   }
         * }
         */
        val res = httpClient.newCall(req).execute()
        val resBody = json.decodeFromString<JsonObject>(res.body?.string()!!)
        return resBody["data"]
            ?.jsonObject
            ?.get("payload")
            ?.jsonObject
            ?.get("token")
            ?.jsonPrimitive
            ?.content!!
    }

    /**
     * 토큰 갱신 API 실패시 재시도 하는 함수
     */
    private fun reqRetryRefreshToken(): Single<String> {
        return Single.create { emitter ->
            try {
                val res = reqRefreshToken()
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
                    if (retryCount <= maxRetries && err is Throwable) {
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
