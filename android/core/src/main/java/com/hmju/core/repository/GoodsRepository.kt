package com.hmju.core.repository

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

/**
 * Description : 상품 Repository Class
 *
 * Created by juhongmin on 2022/01/12
 */
interface GoodsRepository {
    fun fetchGoods(params: GoodsParamMap): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    suspend fun fetchGoodsCo(params: GoodsParamMap): ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    fun fetchGoodsCall(params: GoodsParamMap): Call<ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>>

    fun fetchTest(): Single<JSendObj<TestEntity>>

    fun postLike(body: LikeRequestBody): Single<JSendObj<LikeEntity>>

    fun deleteLike(id: Long): Single<JSendObj<LikeEntity>>
}
