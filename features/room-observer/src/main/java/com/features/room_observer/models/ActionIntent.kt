package com.features.room_observer.models

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
enum class ActionIntent {
	INIT, // 초기 3개만
	LOAD_MORE, // INIT + (Page * 5)
	FORCE_REFRESH,
	FOLD
}