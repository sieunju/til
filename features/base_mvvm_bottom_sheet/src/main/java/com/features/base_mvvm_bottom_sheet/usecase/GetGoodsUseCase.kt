package com.features.base_mvvm_bottom_sheet.usecase

import com.features.base_mvvm_bottom_sheet.models.ui.Goods
import com.features.base_mvvm_bottom_sheet.source.ApiService
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
    suspend operator fun invoke(params: PagingQueryParams): List<Goods> {
        val res = apiService.fetchGoods(params.getQueryMap()).getOrNull() ?: return listOf()
        return res.list.map { Goods(it) }
    }
}