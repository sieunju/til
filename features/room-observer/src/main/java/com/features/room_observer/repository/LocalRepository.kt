package com.features.room_observer.repository

import com.features.room_observer.models.Goods
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
interface LocalRepository {
	fun findAllUserIdObserver(userId: String): Flowable<List<Goods>>
	fun replaceAll(list: List<Goods>): Single<List<Long>>
	fun updateAll(list: List<Goods>): Single<List<Int>>
	fun isContentsARx(): Single<Boolean>
	fun isContentsA(): Boolean
	fun saveContentsType(isContentsA: Boolean)
}