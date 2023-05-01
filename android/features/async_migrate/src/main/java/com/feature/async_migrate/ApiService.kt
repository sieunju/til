package com.feature.async_migrate

import com.hmju.core.model.base.ApiResponse
import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.meta.CustomMetaEntity
import com.hmju.core.model.params.GoodsParamMap
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
        @QueryMap(encoded = true) params: GoodsParamMap
    ): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/til/goods")
    suspend fun fetchCoGoods(
        @QueryMap(encoded = true) params: GoodsParamMap
    ) : ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>
}