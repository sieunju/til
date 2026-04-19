package com.features.network_jsend_format.usecase

import com.features.network_jsend_format.ApiService
import com.features.network_jsend_format.models.entity.JSendTestEntity
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
    suspend operator fun invoke(): JSendTestEntity {
        return apiService.fetchJSendWithMeta()
            .getOrDefault(JSendTestEntity())
    }
}