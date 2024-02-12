package com.features.recyclerview.usecase

import com.features.recyclerview.ApiService
import com.hmju.core.like_manager.LikeManager
import com.hmju.core.model.body.LikeRequestBody
import com.features.recyclerview.models.entity.LikeEntity
import com.hmju.core.rxbus.RxBus
import com.hmju.core.rxbus.SimpleLikeEvent
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AddLikeUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(body: LikeRequestBody): Single<LikeEntity> {
        return apiService.postLike(body)
            .map {
                LikeManager.addLike(body.id)
                RxBus.publish(SimpleLikeEvent(true, body.id))
                return@map it.payload
            }
    }
}