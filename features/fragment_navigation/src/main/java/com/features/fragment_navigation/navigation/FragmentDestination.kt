package com.features.fragment_navigation.navigation

import androidx.fragment.app.Fragment

/**
 * Description : 이동할 화면 정의
 * [tag] 는 화면의 논리적 정체성 (SINGLE_TOP 재사용 매칭 기준)
 * [createFragment] 는 실제 새 인스턴스가 필요할 때만 호출되는 factory
 *
 * Created by juhongmin on 2026. 7. 26.
 */
class FragmentDestination(
    val tag: String,
    val createFragment: () -> Fragment
)
