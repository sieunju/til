package com.features.compose_navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Description :
 *
 * Created by juhongmin on 4/7/24
 */
enum class Screens(
    val destination: String,
    val arguments: List<NamedNavArgument> = listOf()
) {
    LOGIN("login?user_id={user_id}&user_pw={user_pw}"),
    SIGNUP("signup");

    fun getNavGraph(
        builder: NavGraphBuilder,
        content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
    ) {
        return builder.composable(
            route = destination,
            arguments = arguments,
            content = content
        )
    }
}
