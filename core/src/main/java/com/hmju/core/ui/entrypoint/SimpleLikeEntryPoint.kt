package com.hmju.core.ui.entrypoint

import com.hmju.core.usecase.AddLikeUseCase
import com.hmju.core.usecase.RemoveLikeUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface SimpleLikeEntryPoint {
    fun addLikeUseCase(): AddLikeUseCase
    fun removeLikeUseCase(): RemoveLikeUseCase
}
