package com.hmju.core_navigator

import android.net.Uri

/**
 * Description : 각 모듈별 Route Path 정의
 * Default scheme https
 * Default Host til.com
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
    ROOM_OBSERVER("/room/observer"),
    BASE_MVVM_BOTTOM_SHEET("/base/mvvm/bottom_sheet"),
    NETWORK_ERROR_HANDLING("/network/error_handling"),
    NETWORK_EXPIRED_TOKEN("/network/expired_token"),
    NETWORK_JSEND_FORMAT("/network/jsend_format"),
    NETWORK_REFACTOR_V2("/network/refactor/v2");

    fun getUri(): Uri {
	return getUri { }
    }

    fun getUri(params: Uri.Builder.() -> Unit): Uri {
	val builder = Uri.Builder()
	    .scheme("https")
	    .authority("til.com")
	    .path(path)
	params(builder)
	return builder.build()
    }

    companion object {
	fun from(path: String?): Route? {
	    if (path.isNullOrEmpty()) return null
	    return entries.find { it.path == path }
	}
    }
}