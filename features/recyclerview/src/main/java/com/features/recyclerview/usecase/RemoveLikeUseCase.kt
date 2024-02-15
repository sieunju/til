package com.features.recyclerview.usecase

import com.features.recyclerview.ApiService
import com.hmju.core.like_manager.LikeManager
import com.features.recyclerview.models.entity.LikeEntity
import com.hmju.core.rxbus.RxBus
import com.hmju.core.rxbus.SimpleLikeEvent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description : 좋아요 해제 UseCase
 *
 * Created by juhongmin on 2023/03/27
 */
class RemoveLikeUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(id: Long): Single<LikeEntity> {
        return apiService.deleteLike(id)
            .map { it.obj }
            .map {
                LikeManager.removeLike(id)
                RxBus.publish(SimpleLikeEvent(false, id))
                return@map it
            }
    }
}