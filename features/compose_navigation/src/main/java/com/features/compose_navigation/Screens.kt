package com.features.compose_navigation

/**
 * Description :
 *
 * Created by juhongmin on 4/7/24
 */
enum class Screens(
    val destination: String
) {
    LOGIN("login");

    override fun toString(): String {
        return destination
    }
}
