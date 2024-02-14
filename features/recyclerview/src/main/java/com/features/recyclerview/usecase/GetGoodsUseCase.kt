package com.features.recyclerview.usecase

import com.features.recyclerview.ApiService
import com.features.recyclerview.models.entity.GoodsEntity
import com.hmju.core.models.params.GoodsParameter
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetGoodsUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(params: GoodsParameter): Single<List<GoodsEntity>> {
        return apiService.fetchGoods(params.getQueryParameter())
            .map { it.payload }
    }
}
