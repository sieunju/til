package com.features.rv_refactor_diff_util

import com.features.rv_refactor_diff_util.models.entity.GoodsEntity
import com.features.rv_refactor_diff_util.models.meta.CustomMetaEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendList
import com.hmju.core.models.base.JSendListWithMeta
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
 */
interface ApiService {

    @GET("/api/v1/til/jsend/list")
    suspend fun fetchJSendList(): ApiResponse<JSendList<String>>

    @GET("/api/v1/til/goods")
    suspend fun fetchGoods(
        @QueryMap(encoded = true) params: Map<String, String>
    ): ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>
}
