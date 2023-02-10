package com.features.network.usecase

import com.hmju.core.repository.JSendRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetErrorTestUseCase @Inject constructor(
    private val repository: JSendRepository
) {
    operator fun invoke(): Single<String> {
        return repository.fetchErrorTest()
            .map { it.payload }
    }
}