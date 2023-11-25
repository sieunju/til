package com.features.network_v2

import com.features.network_v2.model.JSendEntity
import com.features.network_v2.model.TokenBody
import com.features.network_v2.model.TokenEntity
import com.hmju.core.model.base.ApiResponse
import com.hmju.core.model.base.JSendList
import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.base.JSendObj
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.meta.CustomMetaEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 11/22/23
 */
interface ApiService {
    @GET("/api/til/goods")
    suspend fun fetchGoods(
        @QueryMap(encoded = true) params: Map<String, String>,
    ): ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/til/jsend")
    suspend fun fetchJSend(): ApiResponse<JSendList<JSendEntity>>

    @GET("/api/til/error/404")
    suspend fun fetchError404(): ApiResponse<JSendObj<JSendEntity>>

    @POST("/api/til/auth/refresh")
    suspend fun postToken(
        @Body body: TokenBody,
    ): ApiResponse<JSendObj<TokenEntity>>

    @GET("/api/til/jsend")
    fun fetchJSendRx(): Single<JSendObj<JSendEntity>>

    @GET("/api/til/goods")
    fun fetchGoodsRx(
        @QueryMap(encoded = true) params: Map<String, String>,
    ): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/til/error/404")
    fun fetchError404Rx(): Single<JSendObj<JSendEntity>>

    @POST("/api/til/auth/refresh")
    fun postTokenRx(
        @Body body: TokenBody,
    ): Single<JSendObj<TokenEntity>>
}