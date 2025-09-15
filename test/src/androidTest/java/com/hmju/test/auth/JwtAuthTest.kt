package com.hmju.test.auth

import android.util.Base64
import com.hmju.core.models.auth.AuthTokenEntity
import com.hmju.core.network.NetworkConfig
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlinx.serialization.json.put
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

/**
 * Description :
 *
 * Created by juhongmin on 2/11/24
 */
@HiltAndroidTest
class JwtAuthTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val client: OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(NetworkConfig.CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout(NetworkConfig.READ_TIME_OUT, TimeUnit.MILLISECONDS)
        .writeTimeout(NetworkConfig.WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
        .build()

    private val jsonFormat: Json = Json {
        isLenient = true // Json 큰따옴표 느슨하게 체크.
        ignoreUnknownKeys = true // Field 값이 없는 경우 무시
        coerceInputValues = true // "null" 이 들어간경우 default Argument 값으로 대체
    }

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun 토큰_발급후_리프레쉬_토큰요청() {
        val tokenEntity = reqCreateToken()
        val refreshToken = reqRefreshToken(tokenEntity.refreshToken)
        val refreshExpiredAt = getTokenExpiredMs(refreshToken.refreshToken)
        val sdf = SimpleDateFormat("yyyy.MM.dd HH:mm:sss")
        println("Date ${sdf.format(refreshExpiredAt)}")
        assert(refreshExpiredAt >= 30 * 60 * 1000)
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun reqCreateToken(): AuthTokenEntity {
        val reqBody = buildJsonObject {
            put("email", "test@gmail.com")
            put("expired_minute", 5)
        }

        val req = Request.Builder()
            .url("https://til.qtzz.synology.me/api/v1/auth/token")
            .header("accept", "application/json")
            .header("Content-Type", "application/json")
            .post(reqBody.toString().toRequestBody())
            .build()

        val res = client.newCall(req).execute()
        val resBody = jsonFormat.decodeFromString<JsonObject>(res.body?.string()!!)

        /**
         * {
         *  data: {
         *      payload: {
         *          "access_token": "string",
         *          "access_expired_date": "2024-02-11T08:17:04.505Z",
         *          "refresh_token": "string",
         *          "refresh_expired_date": "2024-02-11T08:17:04.505Z"
         *      }
         * }
         */
        val contents = resBody["data"]
            ?.jsonObject
            ?.get("payload")
            ?.jsonObject!!.toString()
        return jsonFormat.decodeFromString<AuthTokenEntity>(contents)
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun reqRefreshToken(
        refreshToken: String
    ): AuthTokenEntity {
        val req = Request.Builder()
            .header("Authorization", "Bearer $refreshToken")
            .header("accept", "application/json")
            .header("Content-Type", "application/json")
            .url("https://til.qtzz.synology.me/api/v1/auth/refresh")
            .post("".toRequestBody())
            .build()

        val res = client.newCall(req).execute()
        val resBody = jsonFormat.decodeFromString<JsonObject>(res.body?.string()!!)

        /**
         * {
         *  data: {
         *      payload: {
         *          "access_token": "string",
         *          "access_expired_date": "2024-02-11T08:17:04.505Z",
         *          "refresh_token": "string",
         *          "refresh_expired_date": "2024-02-11T08:17:04.505Z"
         *      }
         * }
         */
        val contents = resBody["data"]
            ?.jsonObject
            ?.get("payload")
            ?.jsonObject!!.toString()
        return jsonFormat.decodeFromString<AuthTokenEntity>(contents)
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getTokenExpiredMs(token: String): Long {
        return try {
            val startIdx = token.indexOf(".")
            val endIdx = token.lastIndexOf(".")
            val bytes = Base64.decode(token.substring(startIdx, endIdx), Base64.DEFAULT)
            val json = jsonFormat.decodeFromString<JsonObject>(String(bytes, Charsets.UTF_8))
            json["exp"]?.jsonPrimitive?.long!! * 1000
        } catch (ex: Exception) {
            System.currentTimeMillis()
        }
    }
}
