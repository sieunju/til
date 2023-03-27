package com.features.base_mvvm.usecase

import com.features.base_mvvm.ApiService
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.params.GoodsParamMap
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetGoodsUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(params: GoodsParamMap): Single<List<GoodsEntity>> {
        return apiService.fetchGoods(params)
            .map { it.payload }
    }
}
