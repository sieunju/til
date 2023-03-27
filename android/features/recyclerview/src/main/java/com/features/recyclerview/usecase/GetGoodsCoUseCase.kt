package com.features.recyclerview.usecase

import com.features.recyclerview.ApiService
import com.hmju.core.model.base.ApiResponse
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.params.GoodsParamMap
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2023/02/04
 */
class GetGoodsCoUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(
        params: GoodsParamMap,
        delay: Long
    ): List<GoodsEntity> {
        val response = apiService.fetchGoodsCo(params)
        delay(delay)
        // return response
        return if (response is ApiResponse.Success) {
            response.data.payload
        } else {
            emptyList()
        }
    }
}
