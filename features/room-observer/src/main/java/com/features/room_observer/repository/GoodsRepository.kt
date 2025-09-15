package com.features.room_observer.repository

import com.features.room_observer.models.Goods
import io.reactivex.rxjava3.core.Single

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
interface GoodsRepository {
	fun fetch(pageNo: Int): Single<List<Goods>>
	fun fetchById(id: Long): Single<Goods>
	fun fetchMaxCount(): Single<Int>
	fun fetchIsContentsA(): Single<Boolean>
}