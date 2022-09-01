package com.hmju.core.ui.usecase

import com.hmju.core.model.body.LikeRequestBody
import com.hmju.core.model.like.LikeEntity
import com.hmju.core.repository.GoodsRepository
import com.hmju.core.like_manager.LikeManager
import com.hmju.core.rxbus.RxBus
import com.hmju.core.rxbus.SimpleLikeEvent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description : 좋아요 추가 처리하는 UseCase
 *
 * Created by juhongmin on 2022/01/14
 */
class AddLikeUseCase @Inject constructor(
    private val repository: GoodsRepository
) {
    operator fun invoke(body: LikeRequestBody): Single<LikeEntity> {
        return repository.postLike(body)
            .map {
                LikeManager.addLike(body.id)
                RxBus.publish(SimpleLikeEvent(true, body.id))
                return@map it.payload
            }
    }
}
