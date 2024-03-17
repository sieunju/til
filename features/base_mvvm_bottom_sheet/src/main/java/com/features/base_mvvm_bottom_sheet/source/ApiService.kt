package com.features.base_mvvm_bottom_sheet.source

import com.features.base_mvvm_bottom_sheet.models.entity.GoodsEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendList
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 3/14/24
 */
interface ApiService {
    @GET("/api/til/goods")
    suspend fun fetchGoods(
        @QueryMap(encoded = true) params: Map<String, String>
    ): ApiResponse<JSendList<GoodsEntity>>
}
