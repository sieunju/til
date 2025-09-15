package com.features.base_mvvm_lifecycle.usecase

import com.features.base_mvvm_lifecycle.models.ui.GoodsModel
import com.features.base_mvvm_lifecycle.source.ApiService
import com.hmju.core.models.base.getOrNull
import com.hmju.core.models.params.PagingQueryParams
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 3/14/24
 */
class GetGoodsUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(params: PagingQueryParams): List<GoodsModel> {
        val res = apiService.fetchGoods(params.getQueryMap()).getOrNull() ?: return listOf()
        return res.list.map { GoodsModel(it) }
    }
}