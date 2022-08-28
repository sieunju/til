package com.hmju.network

import com.hmju.core.data.model.auth.TokenEntity
import com.hmju.core.data.model.base.JSendObj
import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST

/**
 * Description : 토큰 만료시 토근 다시 받는 Api Service
 *
 * Created by juhongmin on 2022/01/13
 */
interface RefreshTokenApiService {
    @POST("/api/auth/refresh")
    fun tokenRefresh(): Single<JSendObj<TokenEntity>>
}