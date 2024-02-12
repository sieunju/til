package com.features.network.usecase

import com.features.network.ApiService
import com.hmju.core.model.base.JSendList
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetJSendListUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(): Single<JSendList<String>> {
        return apiService.fetchJSendListRx().onErrorReturn { JSendList() }
    }
}