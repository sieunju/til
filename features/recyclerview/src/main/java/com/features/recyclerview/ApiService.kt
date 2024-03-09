package com.features.recyclerview

import com.features.recyclerview.models.entity.GoodsEntity
import com.features.recyclerview.models.meta.CustomMetaEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendList
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.MetaEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

/**
 * Description : Network Feature 에 필요한 API Service 정의
 *
 * Created by juhongmin on 2023/03/27
 */
interface ApiService {
    @GET("/api/v1/til/goods")
    fun fetchGoods(
        @QueryMap(encoded = true) params: Map<String, String>
    ): Single<JSendListWithMeta<GoodsEntity, MetaEntity>>

    @GET("/api/v1/til/goods")
    suspend fun fetchGoodsCo(
        @QueryMap(encoded = true) params: Map<String, String>
    ): ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/v1/til/jsend/list")
    suspend fun fetchCoJSendList(): ApiResponse<JSendList<String>>
}
