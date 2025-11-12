package com.hmju.core_navigator

import android.net.Uri

/**
 * Description : 각 모듈별 Route Path 정의
 * Default scheme https
 * Default Host til
 * Created by juhongmin on 2025. 11. 9.
 */
enum class Route(
    val path: String
) {
    MAIN("/main"),
    NETWORK("/network"),
    ASYNC_MIGRATE("/async/migrate"),
    BASE_MVVM("/base/mvvm"),
    BASE_MVVM_LIFECYCLE("/base/mvvm/lifecycle"),
    COMPOSE_NAVIGATION("/compose/navigation"),
    COMPOSE_PERMISSIONS_RESULT("/compose/permissions/result"),
    COMPOSE_UI("/compose/ui"),
    RECYCLERVIEW("/recyclerview"),
    ROOM_OBSERVER("/room/observer");

    fun getUri(): Uri {
	return getUri { }
    }

    fun getUri(params: Uri.Builder.() -> Unit): Uri {
	val builder = Uri.Builder()
	    .scheme("https")
	    .authority("til")
	    .path(path)
	params(builder)
	return builder.build()
    }
}