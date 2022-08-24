package com.features.core_ui.entrypoints

import com.features.core_ui.usecase.AddLikeUseCase
import com.features.core_ui.usecase.RemoveLikeUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SimpleLikeEntryPoint {
    fun addLikeUseCase(): AddLikeUseCase
    fun removeLikeUseCase(): RemoveLikeUseCase
}
