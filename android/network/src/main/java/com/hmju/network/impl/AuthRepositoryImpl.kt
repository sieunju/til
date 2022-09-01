package com.hmju.network.impl

import com.hmju.core.model.auth.TokenEntity
import com.hmju.core.model.base.JSendObj
import com.hmju.network.AuthApiService
import com.hmju.core.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description : Auth Repository 구현체 클래스
 *
 * Created by juhongmin on 2022/01/12
 */
internal class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository {

    override fun tokenRefresh(): Single<JSendObj<TokenEntity>> {
        return authApiService.tokenRefresh()
    }

    override fun tokenExpired(): Single<JSendObj<TokenEntity>> {
        return authApiService.tokenExpired()
    }
}