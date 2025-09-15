package com.features.rv_custom_paging

import com.features.rv_custom_paging.models.entity.GoodsEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.MetaEntity
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 2/15/24
 */
interface ApiService {
    @GET("/api/v1/til/goods")
    suspend fun fetchGoods(
        @QueryMap(encoded = true) params: Map<String, String>
    ): ApiResponse<JSendListWithMeta<GoodsEntity, MetaEntity>>
}