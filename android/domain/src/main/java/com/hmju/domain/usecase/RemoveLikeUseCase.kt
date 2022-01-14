package com.hmju.domain.usecase

import com.hmju.domain.repository.GoodsRepository
import com.til.model.body.LikeRequestBody
import com.til.model.like.LikeEntity
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
    operator fun invoke(body: LikeRequestBody): Single<LikeEntity> {
        return repository.deleteLike(body)
            .map { it.data ?: throw NullPointerException("Data is Null") }
    }
}