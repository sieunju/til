package com.features.network.usecase

import com.hmju.core.model.base.JSendObjWithMeta
import com.hmju.core.model.meta.CustomMetaEntity
import com.hmju.core.model.test.JSendTestEntity
import com.hmju.core.repository.JSendRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/08/24
 */
class GetJSendWithMetaUseCase @Inject constructor(
    private val repository: JSendRepository
) {
    operator fun invoke(): Single<JSendObjWithMeta<JSendTestEntity, CustomMetaEntity>> {
        return repository.fetchJSendWithMeta()
    }
}