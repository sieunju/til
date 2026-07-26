package com.features.fragment_navigation.navigation

/**
 * Description : Activity 의 launchMode(standard/singleTop) + Intent.FLAG_ACTIVITY_CLEAR_TOP 을
 * SingleActivity 안의 Fragment 스택에 그대로 옮겨온 개념
 *
 * Created by juhongmin on 2026. 7. 26.
 */
enum class LaunchMode {
    /** 항상 새 Fragment 인스턴스를 만들어 스택에 쌓는다. */
    STANDARD,

    /** 스택에 동일 destination 이 이미 있으면 새로 만들지 않고 그 인스턴스를 그대로 재사용해서 맨 위로 올린다. */
    SINGLE_TOP,

    /** 스택을 전부 비우고 새 인스턴스 하나만 남긴다. */
    CLEAR_TOP
}
