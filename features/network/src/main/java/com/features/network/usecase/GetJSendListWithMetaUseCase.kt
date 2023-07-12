package com.features.network.usecase

import com.features.network.ApiService
import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.base.MetaEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetJSendListWithMetaUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(): Single<JSendListWithMeta<String, MetaEntity>> {
        return apiService.fetchJSendListWithMeta()
    }
}
