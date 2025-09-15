package com.hmju.core.compose

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Description : TIL Compose Color
 *
 * Created by juhongmin on 1/20/24
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
@Immutable
class TilColor internal constructor(
    val white: Color,
    val black: Color,
    val blue: Color,
    val red: Color,
    val purple: Color,
    val yellow: Color,
    val gray1: Color,
    val gray2: Color,
    val gray3: Color,
    val gray3Light: Color,
    val gray4: Color,
    val gray5: Color,
    val defBgColor: Color
) {
    constructor() : this(
        white = Color(255, 255, 255),
        black = Color(34, 34, 34),
        blue = Color(4, 33, 179),
        red = Color(247, 68, 93),
        purple = Color(88, 86, 214),
        yellow = Color(255, 198, 51),
        gray1 = Color(102, 102, 102),
        gray2 = Color(153, 153, 153),
        gray3 = Color(204, 204, 204),
        gray3Light = Color(229, 229, 229),
        gray4 = Color(240, 240, 240),
        gray5 = Color(247, 247, 247),
        defBgColor = Color(245,243,244)
    )
}
