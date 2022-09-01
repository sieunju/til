package com.features.network.usecase

import com.hmju.core.model.base.JSendObj
import com.hmju.core.model.test.JSendTestEntity
import com.hmju.core.repository.JSendRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetJSendUseCase @Inject constructor(
    private val repository: JSendRepository
) {
    operator fun invoke(): Single<JSendObj<JSendTestEntity>> {
        return repository.fetchJSend()
    }
}