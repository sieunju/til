package com.hmju.core.login_manager

import android.util.Base64
import androidx.core.content.edit
import com.hmju.core.BuildConfig
import com.hmju.core.pref.PreferenceManager
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import javax.inject.Inject

/**
 * Description : 로그인 메니저 구현체 클래스
 *
 * Created by juhongmin on 2022/01/12
 */
class LoginManagerImpl @Inject constructor(
    private val prefManager: PreferenceManager
) : LoginManager {

    private var expiredMs: Long = 0
    private var accessToken: String = ""

    private val jsonFormat: Json = Json {
        isLenient = true // Json 큰따옴표 느슨하게 체크.
        ignoreUnknownKeys = true // Field 값이 없는 경우 무시
        coerceInputValues = true // "null" 이 들어간경우 default Argument 값으로 대체
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

    @Synchronized
    override fun setToken(
        token: String
    ) {
        // 토큰 전처리 가공
        prefManager.getPref().edit {
            expiredMs = getTokenExpiredMs(token)
            accessToken = "${BuildConfig.AUTH_TYPE} $token"
            putLong(PreferenceManager.KEY_TOKEN_EXPIRED_MS, expiredMs)
            putString(PreferenceManager.KEY_TOKEN, token)
        }
    }

    override fun getToken(): String {
//        if (accessToken.isEmpty()) {
//            accessToken = "${BuildConfig.AUTH_TYPE} ${prefManager.getString(PreferenceManager.KEY_TOKEN)}"
//        }
        return "${BuildConfig.AUTH_TYPE} ${prefManager.getString(PreferenceManager.KEY_TOKEN)}"
    }

    override fun setRefreshToken(
        token: String
    ) {
        prefManager.setValue(PreferenceManager.KEY_REFRESH_TOKEN, token)
    }

    override fun getRefreshToken(): String {
        return prefManager.getString(PreferenceManager.KEY_REFRESH_TOKEN)
    }

    override fun isLogin(): Boolean {
        return getToken().isNotEmpty()
    }

    override fun getTokenExpiredMs(): Long {
         return prefManager.getLong(PreferenceManager.KEY_TOKEN_EXPIRED_MS)
    }
}