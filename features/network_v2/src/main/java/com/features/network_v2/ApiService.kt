package com.features.network_v2

import com.features.network_v2.model.JSendEntity
import com.hmju.core.model.base.ApiResponse
import com.hmju.core.model.base.JSendList
import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.meta.CustomMetaEntity
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 11/22/23
 */
interface ApiService {
    @GET("/api/til/goods")
    suspend fun fetchGoods(
        @QueryMap(encoded = true) params: Map<String, Any>
    ): ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/til/jsend")
    suspend fun fetchJSend(): ApiResponse<JSendList<JSendEntity>>


}