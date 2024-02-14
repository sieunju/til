package com.features.network.usecase

import com.features.network.ApiService
import com.hmju.core.models.base.JSendObj
import com.features.network.models.entity.JSendTestEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetJSendUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(): Single<JSendObj<JSendTestEntity>> {
        return apiService.fetchJSendRx().onErrorReturn { JSendObj() }
    }
}