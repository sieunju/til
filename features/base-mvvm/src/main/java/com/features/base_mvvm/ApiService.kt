package com.features.base_mvvm

import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.goods.GoodsEntity
import com.hmju.core.models.meta.CustomMetaEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description : Network Feature 에 필요한 API Service 정의
 *
 * Created by juhongmin on 2023/03/27
 */
interface ApiService {
    @GET("/api/til/goods")
    fun fetchGoods(
        @QueryMap(encoded = true) params: Map<String,String>
    ): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>
}
