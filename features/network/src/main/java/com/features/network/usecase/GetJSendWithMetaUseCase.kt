package com.features.network.usecase

import com.features.network.ApiService
import com.hmju.core.model.base.JSendObjWithMeta
import com.hmju.core.model.meta.CustomMetaEntity
import com.hmju.core.model.test.JSendTestEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/08/24
 */
class GetJSendWithMetaUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(): Single<JSendObjWithMeta<JSendTestEntity, CustomMetaEntity>> {
        return apiService.fetchJSendWithMeta()
    }
}