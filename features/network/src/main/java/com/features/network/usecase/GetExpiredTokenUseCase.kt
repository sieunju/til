package com.features.network.usecase

import com.features.network.ApiService
import com.hmju.core.model.auth.TokenEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetExpiredTokenUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(): Single<TokenEntity> {
        return apiService.postTokenExpired()
            .map { it.payload }
    }
}