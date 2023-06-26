package com.feature.async_migrate.usecase

import com.feature.async_migrate.ApiService
import com.hmju.async_migrate.AsyncConverterUtils.toCoroutine
import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.base.getOrDefault
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.params.GoodsParamMap
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2023/05/01
 */
class GetGoodsUseCaseCo @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(queryMap: GoodsParamMap): List<GoodsEntity> {
        return coroutineScope {
            val work1 = async { apiService.fetchCoGoods(queryMap) }
            val work2 = apiService.fetchGoods(queryMap).toCoroutine()
            val res1 = work1.await()
            val res2 = work2
            val list = mutableListOf<GoodsEntity>()
            list.addAll(res1.getOrDefault(JSendListWithMeta()).payload)
            list.addAll(res2.payload)
            list
        }
    }
}