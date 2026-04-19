package com.features.network_expired_token.usecase

import com.features.network_expired_token.ApiService
import com.features.network_expired_token.models.entity.JSendTestEntity
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
