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
    MAIN("main");

    fun getUri(params: Uri.Builder.() -> Unit): Uri {
	val builder = Uri.Builder()
	builder.scheme("https")
	builder.authority("til")
	params(builder)
	return builder.build()
    }
}