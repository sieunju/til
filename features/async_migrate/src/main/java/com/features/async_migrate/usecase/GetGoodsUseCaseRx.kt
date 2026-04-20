package com.features.async_migrate.usecase

import com.features.async_migrate.ApiService
import com.features.async_migrate.models.entity.GoodsDTO
import com.features.async_migrate.models.meta.CustomMetaDTO
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.params.PagingQueryParams
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
        params: PagingQueryParams
    ): Single<JSendListWithMeta<GoodsDTO, CustomMetaDTO>> {
        return apiService.fetchGoods(params.getQueryMap())
    }
}