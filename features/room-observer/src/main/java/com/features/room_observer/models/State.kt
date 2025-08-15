package com.features.room_observer.models

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
sealed interface State {
	data object Skip : State

	data object Loading : State

	data object Empty : State

	data class ContentsA(
		val list: List<Goods>,
		val hasMore: Boolean
	) : State

	data class ContentsB(
		val list: List<Goods>
	) : State

	data object Hidden : State
}
