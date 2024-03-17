package com.features.network_jsend_format.usecase

import com.features.network_jsend_format.ApiService
import com.hmju.core.models.base.JSendObjWithMeta
import com.hmju.core.models.base.MetaEntity
import com.hmju.core.models.base.getOrDefault
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
 */
class GetJSendWithMetaUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(): JSendObjWithMeta<String, MetaEntity> {
        return apiService.fetchJSendWithMeta()
            .getOrDefault(JSendObjWithMeta())
    }
}