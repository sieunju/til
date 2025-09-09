package com.features.room_observer.models

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
sealed interface State {
	data object Skip : State

	data object LogOut : State

	data object Loading : State

	data object Empty : State

	data class AContents(
		val list: List<Goods>,
		val hasMore: Boolean
	) : State {
		override fun toString(): String {
			return "AContents Size:${list.size} hasMore:${hasMore}"
		}
	}

	data class BContents(
		val list: List<Goods>
	) : State
}
