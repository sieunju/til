package com.hmju.core.usecase

import com.hmju.core.like_manager.LikeManager
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
class RemoveLikeUseCase @Inject constructor(
    private val apiService: CommonApiService
){
    operator fun invoke(id: Long) : Single<Boolean> {
        return apiService.deleteLike(id)
            .doOnSuccess {
                LikeManager.removeLike(id)
                RxBus.publish(SimpleLikeEvent(false, id))
            }
            .map { true }
    }
}
