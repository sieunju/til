package com.hmju.network

import com.hmju.core.model.base.ApiResponse
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
 * Description : 상품 API 서비스
 *
 * Created by juhongmin on 2022/01/11
 */
interface GoodsApiService {
    @GET("/api/goods")
    fun fetchGoods(
        @QueryMap(encoded = true) params: GoodsParamMap
    ): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/test")
    fun fetchTest(): Single<JSendObj<TestEntity>>

    @POST("/api/like")
    fun postLike(
        @Body body: LikeRequestBody
    ): Single<JSendObj<LikeEntity>>

    @DELETE("/api/like/{id}")
    fun deleteLike(
        @Path("id") id: Long
    ): Single<JSendObj<LikeEntity>>

    @GET("/api/goods")
    suspend fun fetchGoodsCo(
        @QueryMap(encoded = true) params: GoodsParamMap
    ): ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/goods")
    fun fetchGoodsCall(
        @QueryMap(encoded = true) params: GoodsParamMap
    ): Call<ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>>
}
