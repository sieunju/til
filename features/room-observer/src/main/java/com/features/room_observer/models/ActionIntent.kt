package com.features.room_observer.models

/**
 * Description :
 *
 * Created by juhongmin on 2025. 8. 15.
 */
enum class ActionIntent(
	val text: String
) {
	INIT("초기화"), // 초기 3개만
	LOADED("API 완료"),
	LOAD_MORE("더보기"), // INIT + (Page * 5)
	FORCE_REFRESH("강제 갱신"),
	FOLD("접기");
}