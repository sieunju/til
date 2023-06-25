package com.feature.async_migrate.usecase

import com.feature.async_migrate.ApiService
import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.meta.CustomMetaEntity
import com.hmju.core.model.params.GoodsParamMap
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
        queryMap: GoodsParamMap
    ): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>> {
        return apiService.fetchGoods(queryMap)
    }
}