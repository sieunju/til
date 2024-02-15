package com.features.async_migrate.usecase

import com.features.async_migrate.ApiService
import com.features.async_migrate.models.ui.GoodsModel
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.getOrDefault
import com.hmju.core.models.error.JSendException
import com.hmju.core.models.params.PagingParams
import com.hmju.legacy.async_migrate.toCoroutine
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
    suspend operator fun invoke(params: PagingParams): List<GoodsModel> {
        return coroutineScope {
            val work1 = async { apiService.fetchCoGoods(params.getQueryMap()) }
            val work2 = try {
                apiService.fetchGoods(params.getQueryMap()).toCoroutine()
            } catch (ex: JSendException) {
                println("JTimber 여기가 에러다 $ex")
                JSendListWithMeta()
            }

            val res1 = work1.await()
            val list = mutableListOf<GoodsModel>()
            list.addAll(res1.getOrDefault(JSendListWithMeta()).payload.map { GoodsModel(it) })
            list.addAll(work2.payload.map { GoodsModel(it) })
            list
        }
    }
}