package com.features.rv_simple_like

import com.features.rv_simple_like.models.entity.GoodsEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.MetaEntity
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
interface ApiService {
    @GET("/api/v1/til/goods")
    suspend fun fetchGoods(
        @QueryMap(encoded = true) params: Map<String, String>
    ): ApiResponse<JSendListWithMeta<GoodsEntity, MetaEntity>>
}