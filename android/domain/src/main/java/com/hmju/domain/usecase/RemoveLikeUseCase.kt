package com.hmju.domain.usecase

import com.hmju.domain.repository.GoodsRepository
import com.hmju.likemanager.LikeManager
import com.til.model.like.LikeEntity
import com.til.rxbus.RxBus
import com.til.rxbus.RxBusEvent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description : 좋아요 해제 UseCase
 *
 * Created by juhongmin on 2022/01/14
 */
class RemoveLikeUseCase @Inject constructor(
    private val repository: GoodsRepository
) {
    operator fun invoke(id: Long): Single<LikeEntity> {
        return repository.deleteLike(id)
            .map { it.payload }
            .map {
                LikeManager.removeLike(id)
                RxBus.publish(RxBusEvent.SimpleLikeEvent(false, id))
                return@map it
            }
    }
}