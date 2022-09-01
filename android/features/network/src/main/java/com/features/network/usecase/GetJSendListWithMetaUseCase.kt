package com.features.network.usecase

import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.base.MetaEntity
import com.hmju.core.repository.JSendRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetJSendListWithMetaUseCase @Inject constructor(
    private val repository: JSendRepository
) {
    operator fun invoke(): Single<JSendListWithMeta<String, MetaEntity>> {
        return repository.fetchJSendListWithMeta()
    }
}
