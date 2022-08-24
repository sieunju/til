package com.hmju.core.data.remote

import com.hmju.core.data.model.base.JSendListWithMeta
import com.hmju.core.data.model.base.JSendObj
import com.hmju.core.data.model.body.LikeRequestBody
import com.hmju.core.data.model.goods.GoodsEntity
import com.hmju.core.data.model.like.LikeEntity
import com.hmju.core.data.model.meta.CustomMetaEntity
import com.hmju.core.data.model.params.GoodsParamMap
import com.hmju.core.data.model.test.TestEntity
import io.reactivex.rxjava3.core.Single
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
}
