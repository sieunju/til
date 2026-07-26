package com.features.fragment_navigation.navigation

import androidx.fragment.app.Fragment

/**
 * Description : SingleActivity 안에서 Fragment 스택을 Activity 의
 * standard/singleTop/clearTop 처럼 다루기 위한 Navigator
 *
 * Created by juhongmin on 2026. 7. 26.
 */
interface FragmentNavigator {

    fun navigate(destination: FragmentDestination, launchMode: LaunchMode = LaunchMode.STANDARD)

    /**
     * 뒤로가기 처리. 더 이상 갈 곳이 없으면 false (호출부에서 Activity finish 등 처리)
     */
    fun back(): Boolean

    fun canGoBack(): Boolean

    /** 디버그/데모 표시용 현재 스택 스냅샷 (root -> top 순) */
    fun currentStack(): List<String>

    /** 현재 화면에 보이는(top) Fragment 인스턴스 */
    fun currentFragment(): Fragment?
}

/**
 * 현재 화면에 보이는 Fragment 가 back press 를 먼저 가로채고 싶을 때 구현하는 인터페이스.
 * true 를 반환하면 그 자리에서 소비되고, [FragmentNavigator.back] 은 호출되지 않는다.
 */
fun interface FragmentBackPressHandler {
    fun onBackPressed(): Boolean
}
