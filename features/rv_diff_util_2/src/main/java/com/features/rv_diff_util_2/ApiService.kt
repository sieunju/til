package com.features.rv_diff_util_2

import com.features.rv_diff_util_2.models.entity.GoodsEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.MetaEntity
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("/api/v1/til/goods")
    suspend fun fetch(
        @QueryMap(encoded = true) params: Map<String, String>
    ): ApiResponse<JSendListWithMeta<GoodsEntity, MetaEntity>>
}
