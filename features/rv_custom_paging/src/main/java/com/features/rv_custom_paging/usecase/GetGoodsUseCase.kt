package com.features.rv_custom_paging.usecase

import com.features.rv_custom_paging.ApiService
import com.features.rv_custom_paging.models.ui.GoodsModel
import com.hmju.core.models.base.getOrNull
import com.hmju.core.models.params.PagingQueryParams
import javax.inject.Inject

/**
 * Description : 상품 데이터 가져오는 UseCase
 *
 * Created by juhongmin on 2/15/24
 */
class GetGoodsUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(
        params: PagingQueryParams
    ): List<GoodsModel> {
        val res = apiService.fetchGoods(params.getQueryMap()).getOrNull()
        return res?.list?.map { GoodsModel(it) } ?: listOf()
    }
}