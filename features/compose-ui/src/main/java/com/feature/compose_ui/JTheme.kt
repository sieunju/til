package com.feature.compose_ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle

/**
 * Description : Theme Settings
 *
 * Created by juhongmin on 2023/07/24
 */
object JTheme {
    // 아래 변수로 사용해도 되지만 좀더 편하게 아래 h0..h5 생성
    val textStyle: SimpleTypography
        @Composable
        @ReadOnlyComposable
        get() = localTextTypography.current

    val h0: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = localTextTypography.current.h0

    val h1: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = localTextTypography.current.h1

    val h2: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = localTextTypography.current.h2

    val h3: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = localTextTypography.current.h3

    val h4: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = localTextTypography.current.h4

    val h5: TextStyle
        @Composable
        @ReadOnlyComposable
        get() = localTextTypography.current.h5

}
