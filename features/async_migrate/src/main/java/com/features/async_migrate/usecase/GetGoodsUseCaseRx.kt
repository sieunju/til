package com.features.async_migrate.usecase

import com.features.async_migrate.ApiService
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.goods.GoodsEntity
import com.hmju.core.models.meta.CustomMetaEntity
import com.hmju.core.models.params.GoodsParameter
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2023/05/01
 */
class GetGoodsUseCaseRx @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(
        params: GoodsParameter
    ): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>> {
        return apiService.fetchGoods(params.getQueryParameter())
    }
}