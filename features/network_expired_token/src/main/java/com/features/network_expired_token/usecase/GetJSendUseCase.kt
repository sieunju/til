package com.features.network_expired_token.usecase

import com.features.network_expired_token.ApiService
import com.features.network_expired_token.models.entity.JSendTestEntity
import com.hmju.core.models.base.getOrNull
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
 */
class GetJSendUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(): JSendTestEntity {
        val res = apiService.fetchJSend().getOrNull() ?: return JSendTestEntity("isNull")
        return res.obj
    }
}