package com.features.recyclerview.entrypoint

import com.features.recyclerview.usecase.AddLikeUseCase
import com.features.recyclerview.usecase.RemoveLikeUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Description :
 *
 * Created by juhongmin on 2023/03/27
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface SimpleLikeEntryPoint {
    fun addLikeUseCase(): AddLikeUseCase
    fun removeLikeUseCase(): RemoveLikeUseCase
}
