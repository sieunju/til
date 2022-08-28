package com.hmju.network.impl

import com.hmju.core.data.model.base.JSendListWithMeta
import com.hmju.core.data.model.base.JSendObj
import com.hmju.core.data.model.body.LikeRequestBody
import com.hmju.core.data.model.goods.GoodsEntity
import com.hmju.core.data.model.like.LikeEntity
import com.hmju.core.data.model.meta.CustomMetaEntity
import com.hmju.core.data.model.params.GoodsParamMap
import com.hmju.core.data.model.test.TestEntity
import com.hmju.network.GoodsApiService
import com.hmju.core.data.repository.GoodsRepository
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
