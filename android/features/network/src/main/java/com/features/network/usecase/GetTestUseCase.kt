package com.features.network.usecase

import com.hmju.core.data.model.base.JSendObj
import com.hmju.core.data.model.test.TestEntity
import com.hmju.core.data.repository.GoodsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/08/24
 */
class GetTestUseCase @Inject constructor(
    private val goodsRepository: GoodsRepository
) {
    operator fun invoke(): Single<JSendObj<TestEntity>> {
        return goodsRepository.fetchTest()
    }
}
