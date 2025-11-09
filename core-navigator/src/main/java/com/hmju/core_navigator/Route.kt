package com.hmju.core_navigator

import android.net.Uri

/**
 * Description : 각 모듈별 Route Path 정의
 * 기본 scehem
 * Created by juhongmin on 2025. 11. 9.
 */
enum class Route (
    val path: String
) {
    MAIN("main");

    fun getUri() : Uri{

    }
}