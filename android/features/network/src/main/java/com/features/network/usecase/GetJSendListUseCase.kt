package com.features.network.usecase

import com.hmju.core.data.model.base.JSendList
import com.hmju.core.data.repository.JSendRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetJSendListUseCase @Inject constructor(
    private val repository: JSendRepository
) {
    operator fun invoke(): Single<JSendList<String>> {
        return repository.fetchJSendList()
    }
}