package com.features.network.usecase

import com.features.network.ApiService
import com.features.network.models.entity.JSendTestEntity
import com.hmju.core.models.base.JSendObjWithMeta
import com.hmju.core.models.base.MetaEntity
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
    operator fun invoke(): Single<JSendObjWithMeta<JSendTestEntity, MetaEntity>> {
        return apiService.fetchJSendWithMetaRx()
            .onErrorReturn { JSendObjWithMeta() }
    }
}