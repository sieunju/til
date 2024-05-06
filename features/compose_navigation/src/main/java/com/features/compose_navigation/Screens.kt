package com.features.compose_navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

/**
 * Description :
 *
 * Created by juhongmin on 4/7/24
 */
enum class Screens(
    val destination: String,
    val queryParams: String? = null,
    val arguments: List<NamedNavArgument> = listOf()
) {
    LOGIN(
        destination = "login",
        queryParams = "user_id={id}&user_pw={pw}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                nullable = true
            },
            navArgument("pw") {
                type = NavType.StringType
                nullable = true
            }
        )
    ),
    SIGNUP("signup");

    fun getNavGraph(
        builder: NavGraphBuilder,
        content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
    ) {
        val route = StringBuilder(destination)
        if (!queryParams.isNullOrEmpty()) {
            route.append("?").append(queryParams)
        }
        return builder.composable(
            route = route.toString(),
            arguments = arguments,
            content = content,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween(200)
                ).plus(fadeIn(tween(200)))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start,
                    tween(200)
                ).plus(fadeOut(tween(200)))
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(200)
                ).plus(fadeIn(tween(200)))
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(200)
                ).plus(fadeOut(tween(200)))
            }
        )
    }
}
