package com.features.network.usecase

import com.hmju.core.data.model.auth.TokenEntity
import com.hmju.core.data.repository.AuthRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetExpiredTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Single<TokenEntity> {
        return authRepository.tokenExpired().map { it.payload }
    }
}