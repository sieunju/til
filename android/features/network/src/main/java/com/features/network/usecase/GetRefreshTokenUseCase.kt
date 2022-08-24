package com.features.network.usecase

import com.hmju.core.data.model.auth.TokenEntity
import com.hmju.core.data.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRefreshTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Single<TokenEntity> {
        return authRepository.tokenRefresh()
            .map { it.payload }
    }
}