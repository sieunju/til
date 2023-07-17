package com.features.recyclerview

import com.hmju.core.model.base.ApiResponse
import com.hmju.core.model.base.JSendList
import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.base.JSendObj
import com.hmju.core.model.body.LikeRequestBody
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.like.LikeEntity
import com.hmju.core.model.meta.CustomMetaEntity
import com.hmju.core.model.params.GoodsParamMap
import com.hmju.core.model.test.TestEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

/**
 * Description : Network Feature 에 필요한 API Service 정의
 *
 * Created by juhongmin on 2023/03/27
 */
interface ApiService {
    @GET("/api/til/goods")
    fun fetchGoods(
        @QueryMap(encoded = true) params: GoodsParamMap
    ): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/til/test")
    fun fetchTest(): Single<JSendObj<TestEntity>>

    @POST("/api/til/goods/like")
    fun postLike(
        @Body body: LikeRequestBody
    ): Single<JSendObj<LikeEntity>>

    @DELETE("/api/til/goods/like/{id}")
    fun deleteLike(
        @Path("id") id: Long
    ): Single<JSendObj<LikeEntity>>

    @GET("/api/til/goods")
    suspend fun fetchGoodsCo(
        @QueryMap(encoded = true) params: GoodsParamMap
    ): ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/til/goods")
    fun fetchGoodsCall(
        @QueryMap(encoded = true) params: GoodsParamMap
    ): Call<ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>>

    @GET("/api/til/jsend/list")
    suspend fun fetchCoJSendList(): ApiResponse<JSendList<String>>
}