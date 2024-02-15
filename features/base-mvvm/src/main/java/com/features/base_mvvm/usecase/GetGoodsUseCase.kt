package com.features.base_mvvm.usecase

import com.features.base_mvvm.ApiService
import com.features.base_mvvm.models.ui.GoodsModel
import com.hmju.core.models.params.PagingParams
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetGoodsUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(params: PagingParams): Single<List<GoodsModel>> {
        return apiService.fetchGoods(params.getQueryMap())
            .map { it.list }
            .map { list -> list.map { GoodsModel(it) } }
    }
}
