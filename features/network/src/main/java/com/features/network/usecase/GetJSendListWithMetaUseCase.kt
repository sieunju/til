package com.features.network.usecase

import com.features.network.ApiService
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.MetaEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetJSendListWithMetaUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(): Single<JSendListWithMeta<String, MetaEntity>> {
        return apiService.fetchJSendListWithMetaRx().onErrorReturn { JSendListWithMeta() }
    }
}
