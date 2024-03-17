package com.hmju.core.usecase

import com.hmju.core.like_manager.LikeManager
import com.hmju.core.models.body.LikeRequestBody
import com.hmju.core.network.CommonApiService
import com.hmju.core.rxbus.RxBus
import com.hmju.core.rxbus.SimpleLikeEvent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
class AddLikeUseCase @Inject constructor(
    private val apiService: CommonApiService
) {
    operator fun invoke(body: LikeRequestBody): Single<Boolean> {
        return apiService.postLike(body)
            .doOnSuccess {
                LikeManager.addLike(body.id)
                RxBus.publish(SimpleLikeEvent(true, body.id))
            }
            .map { true }
    }
}