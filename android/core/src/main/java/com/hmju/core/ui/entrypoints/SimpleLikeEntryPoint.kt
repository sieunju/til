package com.hmju.core.ui.entrypoints

import com.hmju.core.ui.usecase.AddLikeUseCase
import com.hmju.core.ui.usecase.RemoveLikeUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SimpleLikeEntryPoint {
    fun addLikeUseCase(): AddLikeUseCase
    fun removeLikeUseCase(): RemoveLikeUseCase
}
