package com.hmju.core.data.repository

import com.hmju.core.data.model.base.JSendListWithMeta
import com.hmju.core.data.model.base.JSendObj
import com.hmju.core.data.model.body.LikeRequestBody
import com.hmju.core.data.model.goods.GoodsEntity
import com.hmju.core.data.model.like.LikeEntity
import com.hmju.core.data.model.meta.CustomMetaEntity
import com.hmju.core.data.model.params.GoodsParamMap
import com.hmju.core.data.model.test.TestEntity
import io.reactivex.rxjava3.core.Single

/**
 * Description : 상품 Repository Class
 *
 * Created by juhongmin on 2022/01/12
 */
interface GoodsRepository {
    fun fetchGoods(params: GoodsParamMap): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    fun fetchTest(): Single<JSendObj<TestEntity>>

    fun postLike(body: LikeRequestBody): Single<JSendObj<LikeEntity>>

    fun deleteLike(id: Long): Single<JSendObj<LikeEntity>>
}
