package com.features.room_observer

import com.features.room_observer.models.GoodsDTO
import com.hmju.core.local.models.GoodsEntity
import com.hmju.core.models.base.JSendList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
interface ApiService {
    @GET("/api/v1/til/goods")
    fun fetchGoods(
        @Query("pageNo") pageNo: Int,
        @Query("pageSize") pageSize: Int = 30
    ): Single<JSendList<GoodsDTO>>
}
