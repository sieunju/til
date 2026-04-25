package com.features.rv_diff_util_2.usecase

import com.features.rv_diff_util_2.ApiService
import com.features.rv_diff_util_2.models.ui.Goods
import com.hmju.core.models.base.getOrNull
import com.hmju.core.models.params.PagingQueryParams
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 3/10/24
 */
class GetGoodsUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(params: PagingQueryParams): List<Goods> {
        val res = apiService.fetch(params.getQueryMap()).getOrNull()
        return res?.list?.map { Goods(it) } ?: listOf()
    }
}
