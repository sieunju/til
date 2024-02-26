package com.hmju.core.login_manager

/**
 * Description : 회원 로그인 매니저 관련 인터페이스
 *
 * Created by juhongmin on 2022/01/12
 */
interface LoginManager {
    fun setToken(token: String)
    fun getToken(): String
    fun setRefreshToken(token: String)
    fun getRefreshToken(): String
    fun isLogin(): Boolean
    fun getTokenExpiredMs(): Long
}
