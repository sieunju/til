package com.features.room_observer.impl

import com.features.room_observer.models.AccountBusEvent
import com.features.room_observer.models.Goods
import com.features.room_observer.repository.ApiService
import com.features.room_observer.repository.GoodsRepository
import com.hmju.core.rxbus.RxBus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
internal class GoodsRepositoryImpl @Inject constructor(
	private val apis: ApiService
) : GoodsRepository {

	private fun getMember(): Single<AccountBusEvent.Member> {
		return RxBus.behavior(AccountBusEvent.Member::class.java)
			.firstOrError()
			.subscribeOn(Schedulers.io())
	}

	override fun fetch(
		pageNo: Int
	): Single<List<Goods>> {
		return Single.zip(
			getMember(),
			apis.fetchGoods(pageNo, 30).delay(500, TimeUnit.MILLISECONDS)
				.subscribeOn(Schedulers.io())
		) { member, res ->
			return@zip res.list.map { Goods(member.id, it) }
		}.onErrorReturn { listOf() }.subscribeOn(Schedulers.io())
	}

	override fun fetchById(id: Long): Single<Goods> {
		return Single.zip(
			getMember(),
			apis.fetchById(id)
		) { member, res ->
			return@zip Goods(member.id, res.obj)
		}
	}

	override fun fetchMaxCount(): Single<Int> {
		return Single.just(5).subscribeOn(Schedulers.io())
	}

	override fun fetchIsContentsA(): Single<Boolean> {
		return Single.just(Random.nextBoolean())
	}
}