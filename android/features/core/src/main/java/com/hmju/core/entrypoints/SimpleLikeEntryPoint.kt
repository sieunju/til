package com.hmju.core.entrypoints

import com.hmju.domain.usecase.AddLikeUseCase
import com.hmju.domain.usecase.RemoveLikeUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SimpleLikeEntryPoint {
    fun addLikeUseCase(): AddLikeUseCase
    fun removeLikeUseCase(): RemoveLikeUseCase
}