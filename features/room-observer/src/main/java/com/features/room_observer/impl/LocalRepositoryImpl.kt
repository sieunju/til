package com.features.room_observer.impl

import com.features.room_observer.models.Goods
import com.features.room_observer.repository.LocalRepository
import com.hmju.core.local.dao.GoodsDAO
import com.hmju.core.local.models.GoodsEntity
import com.hmju.core.pref.PreferenceManager
import com.hmju.core.util.RxUtil
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
internal class LocalRepositoryImpl @Inject constructor(
	private val dao: GoodsDAO,
	private val prefManager: PreferenceManager
) : LocalRepository {

	companion object {
		private const val KEY_CONTENTS = "ROOM_OBSERVER_CONTENTS"
	}

	override fun findAllUserIdObserver(userId: String): Flowable<List<Goods>> {
		return dao.findAllByUserIdFlowable(userId)
			.map { list -> list.map { Goods(it) } }
			.onErrorReturn { listOf() }
			.compose(RxUtil.flowableMergeIo())
	}

	override fun replaceAll(
		list: List<Goods>
	): Single<List<Long>> {
		return Single.create { emitter ->
			try {
				val replaceList = dao.replaceAll(list.map { it.toEntity() })
				emitter.onSuccess(replaceList)
			} catch (ex: Exception) {
				emitter.onError(ex)
			}
		}.compose(RxUtil.singleMergeIo())
	}

	override fun updateAll(list: List<Goods>): Single<List<Int>> {
		return Single.create<List<Int>> { emitter ->
			try {
				val resultList = list.map { it.toEntity() }
				dao.updateAll(resultList)
				emitter.onSuccess(listOf())
			} catch (ex: Exception) {
				emitter.onError(ex)
			}
		}.compose(RxUtil.singleMergeIo())
	}

	override fun isContentsARx(): Single<Boolean> {
		return Single.create {
			val value = prefManager.getString(KEY_CONTENTS)
			if (value == "Y") {
				it.onSuccess(true)
			} else {
				it.onSuccess(false)
			}
		}.compose(RxUtil.singleMergeIo())
	}

	override fun isContentsA(): Boolean {
		val value = prefManager.getString(KEY_CONTENTS)
		return value == "Y"
	}

	override fun saveContentsType(isContentsA: Boolean) {
		prefManager.setValue(KEY_CONTENTS, if (isContentsA) "Y" else "N")
	}

	private fun Goods.toEntity(): GoodsEntity {
		return GoodsEntity(
			goodsId = this.id,
			userId = this.userId,
			title = this.title,
			message = this.message,
			imagePath = this.imagePath,
			createdAt = LocalDateTime.now().toKotlinLocalDateTime()
		)
	}
}