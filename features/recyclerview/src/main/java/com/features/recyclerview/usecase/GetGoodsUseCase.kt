package com.features.recyclerview.usecase

import com.features.recyclerview.ApiService
import com.features.recyclerview.models.ui.GoodsModel
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
