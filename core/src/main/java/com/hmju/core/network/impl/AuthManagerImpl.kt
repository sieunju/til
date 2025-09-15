package com.hmju.core.network.impl

import com.hmju.core.BuildConfig
import com.hmju.core.models.auth.AuthTokenEntity
import com.hmju.core.network.AuthManager
import com.hmju.core.network.NetworkConfig.Header
import com.hmju.core.pref.PreferenceManager
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.put
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Description : Token 갱신 및 발급 관련 매니저 클래스
 * Warning! 어떤 것도 의존 하면 안된다.
 *
 * Created by juhongmin on 2/12/24
 */
internal class AuthManagerImpl @Inject constructor(
    private val prefManager: PreferenceManager,
    private val trackingInterceptor: Interceptor,
    private val loggingInterceptor: HttpLoggingInterceptor
) : AuthManager {

    private val jsonFormat: Json by lazy {
        Json {
            isLenient = true // Json 큰따옴표 느슨하게 체크.
            ignoreUnknownKeys = true // Field 값이 없는 경우 무시
            coerceInputValues = true // "null" 이 들어간경우 default Argument 값으로 대체
        }
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .callTimeout(3, TimeUnit.SECONDS)
            .apply {
                // if (BuildConfig.DEBUG) {
                addInterceptor(trackingInterceptor)
                addInterceptor(loggingInterceptor)
                // }
            }
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Throws(IOException::class)
    override fun createToken(): AuthTokenEntity {
        val reqBody = buildJsonObject {
            put("email", "til_android@email.com")
            put("expired_minute", 5)
        }

        val req = Request.Builder()
            .url(BuildConfig.BASE_URL.plus("/api/v1/auth/token"))
            .header(Header.KEY_ACCEPT, Header.VAL_ACCEPT)
            .header(Header.KEY_CONTENT, Header.VAL_CONTENT)
            .post(reqBody.toString().toRequestBody())
            .build()
        val res = client.newCall(req).execute()

        val resBody = jsonFormat.decodeFromString<JsonObject>(res.body?.string()!!)
        val contents = resBody["data"]
            ?.jsonObject
            ?.get("payload")
            ?.jsonObject!!.toString()
        return jsonFormat.decodeFromString<AuthTokenEntity>(contents)
    }

    override fun isRefreshToken(): Boolean {
        return prefManager.getString(PreferenceManager.KEY_REFRESH_TOKEN).isNotEmpty()
    }


    @OptIn(ExperimentalSerializationApi::class)
    override fun refreshToken(): AuthTokenEntity {
        val refreshToken = prefManager.getString(PreferenceManager.KEY_REFRESH_TOKEN)
        val req = Request.Builder()
            .url(BuildConfig.BASE_URL.plus("/api/v1/auth/refresh"))
            .header(Header.KEY_AUTH, "${BuildConfig.AUTH_TYPE} $refreshToken")
            .header(Header.KEY_ACCEPT, Header.VAL_ACCEPT)
            .header(Header.KEY_CONTENT, Header.VAL_CONTENT)
            .post("".toRequestBody())
            .build()

        val res = client.newCall(req).execute()
        val resBody = jsonFormat.decodeFromString<JsonObject>(res.body?.string()!!)
        val contents = resBody["data"]
            ?.jsonObject
            ?.get("payload")
            ?.jsonObject!!.toString()
        return jsonFormat.decodeFromString<AuthTokenEntity>(contents)
    }
}