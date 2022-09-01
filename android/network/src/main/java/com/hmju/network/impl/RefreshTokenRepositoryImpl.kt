package com.hmju.network.impl

import com.hmju.core.model.auth.TokenEntity
import com.hmju.core.model.base.JSendObj
import com.hmju.core.repository.RefreshTokenRepository
import com.hmju.network.RefreshTokenApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

internal class RefreshTokenRepositoryImpl @Inject constructor(
    private val apiService: RefreshTokenApiService
) : RefreshTokenRepository {
    override fun fetch(): Single<JSendObj<TokenEntity>> {
        return apiService.tokenRefresh()
    }
}
