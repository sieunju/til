package com.feature.compose_ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

/**
 * Description : Compose UI 시스템에 사용할 킷
 *
 * Created by juhongmin on 2023/07/24
 */
@Immutable
@Suppress("unused","MemberVisibilityCanBePrivate")
class SimpleTypography internal constructor(
    val h0 : TextStyle,
    val h0_B : TextStyle,
    val h0_M : TextStyle,
    val h1 : TextStyle,
    val h1_B : TextStyle,
    val h1_M : TextStyle,
    val h2 : TextStyle,
    val h2_B : TextStyle,
    val h2_M : TextStyle,
    val h3 : TextStyle,
    val h3_B : TextStyle,
    val h3_M : TextStyle,
    val h4 : TextStyle,
    val h4_B : TextStyle,
    val h4_M : TextStyle,
    val h5 : TextStyle,
    val h5_B : TextStyle,
    val h5_M : TextStyle,
) {
    constructor() : this(
        h0 = TextStyle(
            fontSize = 32.sp
        ),
        h0_B = TextStyle(
            fontSize = 32.sp,
            fontStyle = FontStyle.Normal
        ),
        h0_M = TextStyle(
            fontSize = 32.sp,
            fontStyle = FontStyle.Normal
        ),
        h1 = TextStyle(
            fontSize = 28.sp
        ),
        h1_B = TextStyle(
            fontSize = 28.sp,
            fontStyle = FontStyle.Normal
        ),
        h1_M = TextStyle(
            fontSize = 28.sp,
            fontStyle = FontStyle.Normal
        ),
        h2 = TextStyle(
            fontSize = 24.sp
        ),
        h2_B = TextStyle(
            fontSize = 24.sp,
            fontStyle = FontStyle.Normal
        ),
        h2_M = TextStyle(
            fontSize = 24.sp,
            fontStyle = FontStyle.Normal
        ),
        h3 = TextStyle(
            fontSize = 20.sp
        ),
        h3_B = TextStyle(
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal
        ),
        h3_M = TextStyle(
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal
        ),
        h4 = TextStyle(
            fontSize = 16.sp
        ),
        h4_B = TextStyle(
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal
        ),
        h4_M = TextStyle(
            fontSize = 16.sp,
            fontStyle = FontStyle.Normal
        ),
        h5 = TextStyle(
            fontSize = 12.sp
        ),
        h5_B = TextStyle(
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal
        ),
        h5_M = TextStyle(
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal
        )
    )
}

internal val localTextTypography = staticCompositionLocalOf { SimpleTypography() }