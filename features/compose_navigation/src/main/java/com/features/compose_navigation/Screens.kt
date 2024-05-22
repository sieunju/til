package com.features.compose_navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
    val arguments: List<NamedNavArgument> = listOf() // type == StringType 만 가능
) {
    SIGNUP("signup"),
    LOGIN(
        destination = "login",
        arguments = listOf(
            navArgument("user_id") {
                type = NavType.StringType
                nullable = true
            },
            navArgument("user_pw") {
                type = NavType.StringType
                nullable = true
            }
        )
    ),
    MEMO(
        destination = "memo",
        arguments = listOf(
            navArgument("user_id") {
                type = NavType.StringType
            }
        )
    );

    fun getNavGraph(
        builder: NavGraphBuilder,
        content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
    ) {
        val route = StringBuilder(destination)
        if (arguments.isNotEmpty()) {
            route.append("?")
            route.append(arguments.joinToString("&") { "${it.name}={${it.name}}" })
        }
        return builder.composable(
            route = route.toString(),
            arguments = arguments,
            content = content,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(400)
                )
            },
            exitTransition = {
                fadeOut(tween(400))
            },
            popEnterTransition = {
                fadeIn(tween(400))
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(400)
                )
            }
        )
    }

    /**
     * 화면에 정의된 Argument 스펙 기준으로 파라미터 셋팅해서 URL 형식으로 전달하는 함수
     * @param argumentsMap 다음 화면에 전달할 파라미터 데이터
     */
    fun getNavigation(
        argumentsMap: Map<String, Any?> = mapOf()
    ): String {
        val route = StringBuilder(destination)
        if (arguments.isNotEmpty()) {
            route.append("?")
            route.append(arguments.mapNotNull {
                val value = argumentsMap[it.name]
                if (value != null) {
                    "${it.name}=$value"
                } else {
                    null
                }
            }.joinToString("&"))
        }
        return route.toString()
    }
}
