package com.hmju.shared.login_manager

import io.reactivex.rxjava3.core.Single

/**
 * Description : 회원 로그인 매니저 관련 인터페이스
 *
 * Created by juhongmin on 2022/01/12
 */
interface LoginManager {
    fun setToken(token: String)
    fun getToken(): String
    fun isLogin() : Boolean
    fun rxIsLogin() : Single<Boolean>
}
