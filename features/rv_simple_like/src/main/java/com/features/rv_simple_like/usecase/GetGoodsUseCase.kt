package com.features.rv_simple_like.usecase

import com.features.rv_simple_like.ApiService
import com.features.rv_simple_like.models.ui.Goods
import com.hmju.core.models.base.getOrNull
import com.hmju.core.models.params.PagingQueryParams
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
class GetGoodsUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(
        params: PagingQueryParams
    ): List<Goods> {
        val res = apiService.fetchGoods(params.getQueryMap())
            .getOrNull()
            ?: return emptyList()
        return res.list.map { Goods(it) }
    }
}