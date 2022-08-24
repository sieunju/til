package com.hmju.core.data.remote

import com.hmju.core.data.model.auth.TokenEntity
import com.hmju.core.data.model.base.JSendObj
import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST

/**
 * Description : 사용자 인증 관련 API 서비스
 *
 * Created by juhongmin on 2022/01/11
 */
interface AuthApiService {
    @POST("/api/auth/refresh")
    fun tokenRefresh(): Single<JSendObj<TokenEntity>>

    @POST("/api/auth/expired")
    fun tokenExpired(): Single<JSendObj<TokenEntity>>
}
