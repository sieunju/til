package com.features.network_expired_token.usecase

import com.features.network_expired_token.ApiService
import com.hmju.core.models.base.JSendList
import com.hmju.core.models.base.getOrDefault
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
 */
class GetJSendListUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(): JSendList<String> {
        return apiService.fetchJSendList().getOrDefault(JSendList())
    }
}
