package com.features.rv_refactor_diff_util.usecase

import com.features.rv_refactor_diff_util.ApiService
import com.features.rv_refactor_diff_util.models.ui.GoodsModel
import com.hmju.core.models.base.getOrNull
import com.hmju.core.models.params.PagingQueryParams
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
 */
class GetGoodsUseCase @Inject constructor(
    private val apiService: ApiService
) {

    suspend operator fun invoke(
        params: PagingQueryParams,
        delay: Long
    ): List<GoodsModel> {
        val res = apiService.fetchGoods(
            params.getQueryMap()
        ).getOrNull()
        delay(delay)
        return res?.list?.map { GoodsModel(it) } ?: listOf()
    }
}
