package com.features.core_ui.usecase

import com.hmju.core.data.model.like.LikeEntity
import com.hmju.core.data.repository.GoodsRepository
import com.hmju.core.like_manager.LikeManager
import com.hmju.core.rxbus.RxBus
import com.hmju.core.rxbus.SimpleLikeEvent
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
                RxBus.publish(SimpleLikeEvent(false, id))
                return@map it
            }
    }
}