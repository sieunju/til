package com.features.base_mvvm.usecase

import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.params.GoodsParamMap
import com.hmju.core.repository.GoodsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetGoodsUseCase @Inject constructor(
    private val repository: GoodsRepository
) {
    operator fun invoke(params: GoodsParamMap): Single<List<GoodsEntity>> {
        return repository.fetchGoods(params)
            .map { it.payload }
    }
}
