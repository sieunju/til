package com.hmju.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Description : Theme Settings
 *
 * Created by juhongmin on 2023/07/24
 */
object TilTheme {

    private val localTextStyle = staticCompositionLocalOf { TilTextStyle() }
    private val localColorStyle = staticCompositionLocalOf { TilColor() }

    val text: TilTextStyle
        @Composable
        @ReadOnlyComposable
        get() = localTextStyle.current

    val color: TilColor
        @Composable
        @ReadOnlyComposable
        get() = localColorStyle.current

}
