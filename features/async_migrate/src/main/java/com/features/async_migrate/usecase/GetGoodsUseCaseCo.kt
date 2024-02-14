package com.features.async_migrate.usecase

import com.features.async_migrate.ApiService
import com.hmju.async_migrate.AsyncConverterUtils.toCoroutine
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.getOrDefault
import com.hmju.core.models.error.JSendException
import com.hmju.core.models.goods.GoodsEntity
import com.hmju.core.models.params.GoodsParameter
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
    suspend operator fun invoke(params: GoodsParameter): List<GoodsEntity> {
        return coroutineScope {
            val work1 = async { apiService.fetchCoGoods(params.getQueryParameter()) }
            val work2 = try {
                apiService.fetchGoods(params.getQueryParameter()).toCoroutine()
            } catch (ex: JSendException) {
                println("JTimber 여기가 에러다 $ex")
                JSendListWithMeta()
            }

            val res1 = work1.await()
            val res2 = work2
            val list = mutableListOf<GoodsEntity>()
            list.addAll(res1.getOrDefault(JSendListWithMeta()).payload)
            list.addAll(res2.payload)
            list
        }
    }
}