package com.features.network_expired_token.usecase

import com.features.network_expired_token.ApiService
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.MetaEntity
import com.hmju.core.models.base.getOrDefault
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
 */
class GetJSendListWithMetaUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(): JSendListWithMeta<String, MetaEntity> {
        return apiService.fetchJSendListWithMeta().getOrDefault(JSendListWithMeta())
    }
}
