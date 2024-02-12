package com.features.network.usecase

import com.features.network.ApiService
import com.hmju.core.model.base.JSendObj
import com.features.network.models.JSendTestEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetJSendUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(): Single<JSendObj<JSendTestEntity>> {
        return apiService.fetchJSendRx().onErrorReturn { JSendObj() }
    }
}