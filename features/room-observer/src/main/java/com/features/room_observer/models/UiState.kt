package com.features.room_observer.models

import com.hmju.core.compose.BaseUiModel

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
sealed interface UiState {
	data object Unknown : UiState
	data class Loading(
		val uiList: List<BaseUiModel>
	) : UiState {
		constructor() : this(
			(0L..2L).map { LoadingUiModel(it) }
		)
	}

	data object Empty : UiState
	data class AContents(
		val list: List<BaseUiModel>,
		val hasMore: Boolean
	) : UiState {
		constructor(state: State.AContents) : this(
			list = state.list.map { GoodsUiModel(it) },
			hasMore = state.hasMore
		)
	}

	data class BContents(
		val list: List<BaseUiModel>
	) : UiState {
		constructor(state: State.BContents) : this(
			list = state.list.map { GoodsUiModel(it) }
		)
	}

	companion object {
		fun from(state: State): UiState {
			return when (state) {
				is State.LogOut -> Unknown
				is State.Loading -> Loading()
				is State.Empty -> Empty
				is State.AContents -> AContents(state)
				is State.BContents -> BContents(state)
				else -> throw IllegalStateException("Invalidate Type")
			}
		}
	}
}