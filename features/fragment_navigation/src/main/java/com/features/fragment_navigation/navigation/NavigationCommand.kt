package com.features.fragment_navigation.navigation

/**
 * Description : [FragmentNavigationViewModel] 을 통해 전달되는 이동 명령
 *
 * Created by juhongmin on 2026. 7. 27.
 */
sealed interface NavigationCommand {
    data class Navigate(
	val destination: FragmentDestination,
	val launchMode: LaunchMode
    ) : NavigationCommand
}
