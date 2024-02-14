package com.hmju.core.network

import com.hmju.core.models.auth.AuthTokenEntity

/**
 * Description : Token 갱신 및 발급 관련 매니저 클래스
 * Warning! 어떤 것도 의존 하면 안된다.
 *
 * Created by juhongmin on 2/12/24
 */
internal interface AuthManager {
    /**
     * 토큰 발급 처리하는 함수
     */
    fun createToken(): AuthTokenEntity

    /**
     * Refresh Token 이 저장되어 있는지 확인 하는 함수
     * @return true RefreshToken 저장됨
     */
    fun isRefreshToken(): Boolean

    /**
     * 토큰 재발급 처리하는 함수
     */
    fun refreshToken(): AuthTokenEntity
}
