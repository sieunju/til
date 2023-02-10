package com.features.recyclerview.usecase

import com.hmju.core.model.base.ApiResponse
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.params.GoodsParamMap
import com.hmju.core.repository.GoodsRepository
import kotlinx.coroutines.delay
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2023/02/04
 */
class GetGoodsCoUseCase @Inject constructor(
    private val repository: GoodsRepository
) {
    suspend operator fun invoke(params: GoodsParamMap,delay: Long): List<GoodsEntity> {
        val response = repository.fetchGoodsCo(params)
        delay(delay)
        Timber.d("Response $response")
        return if (response is ApiResponse.Success) {
            response.data.payload
        } else {
            emptyList()
        }
    }
}
