package com.features.network.usecase

import com.features.network.ApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetErrorTestUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(): Single<String> {
        return apiService.fetchErrorTestRx()
            .map { it.payload }
            .onErrorReturn { "" }
    }
}