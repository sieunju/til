package com.hmju.network.impl

import com.hmju.core.model.base.ApiResponse
import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.base.JSendObj
import com.hmju.core.model.body.LikeRequestBody
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.model.like.LikeEntity
import com.hmju.core.model.meta.CustomMetaEntity
import com.hmju.core.model.params.GoodsParamMap
import com.hmju.core.model.test.TestEntity
import com.hmju.core.repository.GoodsRepository
import com.hmju.network.GoodsApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description : Goods Repository 구현체 클래스
 *
 * Created by juhongmin on 2022/01/12
 */
internal class GoodsRepositoryImpl @Inject constructor(
    private val goodsApiService: GoodsApiService
) : GoodsRepository {
    override fun fetchGoods(params: GoodsParamMap): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>> {
        return goodsApiService.fetchGoods(params)
    }

    override suspend fun fetchGoodsCo(params: GoodsParamMap): ApiResponse<JSendListWithMeta<GoodsEntity, CustomMetaEntity>> {
        return goodsApiService.fetchGoodsCo(params)
    }

    override fun fetchTest(): Single<JSendObj<TestEntity>> {
        return goodsApiService.fetchTest()
    }

    override fun postLike(body: LikeRequestBody): Single<JSendObj<LikeEntity>> {
        return goodsApiService.postLike(body)
    }

    override fun deleteLike(id: Long): Single<JSendObj<LikeEntity>> {
        return goodsApiService.deleteLike(id)
    }
}
