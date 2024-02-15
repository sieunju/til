package com.features.recyclerview.usecase

import com.features.recyclerview.ApiService
import com.features.recyclerview.models.ui.GoodsModel
import com.hmju.core.models.base.getOrNull
import com.hmju.core.models.params.PagingParams
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
        params: PagingParams,
        delay: Long
    ): List<GoodsModel> {
        val response = apiService.fetchGoodsCo(
            params.getQueryMap()
        ).getOrNull()
        delay(delay)
        return response?.payload?.map { GoodsModel(it) } ?: listOf()
    }
}
