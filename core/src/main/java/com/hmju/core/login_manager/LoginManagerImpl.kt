package com.hmju.core.login_manager

import android.util.Base64
import androidx.core.content.edit
import com.hmju.core.pref.PreferenceManager
import io.reactivex.rxjava3.core.Single
import org.json.JSONObject
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
    private var userToken: String = ""

    private fun getTokenExpiredMs(token: String): Long {
        return try {
            val startIdx = token.indexOf(".")
            val endIdx = token.lastIndexOf(".")
            val bytes = Base64.decode(token.substring(startIdx, endIdx), Base64.DEFAULT)
            val json = JSONObject(String(bytes, Charsets.UTF_8))
            json.getLong("exp") * 1000
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
            userToken = token
            putLong(PreferenceManager.KEY_TOKEN_EXPIRED_MS, expiredMs)
            putString(PreferenceManager.KEY_TOKEN, userToken)
        }
    }

    override fun getToken(): String {
        if (userToken.isEmpty()) {
            userToken = prefManager.getString(PreferenceManager.KEY_TOKEN)
        }
        return userToken
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

    override fun rxIsLogin(): Single<Boolean> {
        return Single.just(getToken().isNotEmpty())
    }

    override fun getTokenExpiredMs(): Long {
        return expiredMs
    }
}