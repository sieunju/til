package com.features.async_migrate

import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.goods.GoodsEntity
import com.hmju.core.models.meta.CustomMetaEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 2023/05/01
 */
interface ApiService {

    @GET("/api/til/goods")
    fun fetchGoods(
        @QueryMap(encoded = true) params: Map<String, String>
    ): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/til/goods")
    suspend fun fetchCoGoods(
        @QueryMap(encoded = true) params: Map<String, String>
    ): ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>
}