package com.features.room_observer.repository

import com.features.room_observer.models.Goods
import com.features.room_observer.models.GoodsParams
import io.reactivex.rxjava3.core.Single

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
interface GoodsRepository {
    fun fetchGoods(params: GoodsParams) : Single<List<Goods>>
}