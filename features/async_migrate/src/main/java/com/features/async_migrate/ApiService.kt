package com.features.async_migrate

import com.features.async_migrate.models.entity.GoodsEntity
import com.features.async_migrate.models.meta.CustomMetaEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendListWithMeta
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 2023/05/01
 */
interface ApiService {

    @GET("/api/v1/til/goods")
    fun fetchGoods(
        @QueryMap(encoded = true) params: Map<String, String>
    ): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/v1/til/goods")
    suspend fun fetchCoGoods(
        @QueryMap(encoded = true) params: Map<String, String>
    ): ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>
}