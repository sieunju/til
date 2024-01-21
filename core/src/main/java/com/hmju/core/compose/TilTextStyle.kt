package com.hmju.core.compose

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.hmju.core.R

/**
 * Description : Compose UI 시스템에 사용할 킷
 *
 * Created by juhongmin on 2023/07/24
 */
@Immutable
@Suppress("unused", "MemberVisibilityCanBePrivate")
class TilTextStyle internal constructor(
    val h0: TextStyle,
    val h0_M: TextStyle,
    val h0_B: TextStyle,
    val h1: TextStyle,
    val h1_M: TextStyle,
    val h1_B: TextStyle,
    val h2: TextStyle,
    val h2_M: TextStyle,
    val h2_B: TextStyle,
    val h3: TextStyle,
    val h3_M: TextStyle,
    val h3_B: TextStyle,
    val h4: TextStyle,
    val h4_M: TextStyle,
    val h4_B: TextStyle,
    val h5: TextStyle,
    val h5_M: TextStyle,
    val h5_B: TextStyle
) {

    companion object {
        private fun TextStyle.changeStyle(default: FontFamily): TextStyle {
            return if (fontFamily != null) this else copy(fontFamily = default)
        }
    }

    constructor(
        regularFont: FontFamily = FontFamily(Font(R.font.inter_regular)),
        mediumFont: FontFamily = FontFamily(Font(R.font.inter_medium)),
        boldFont: FontFamily = FontFamily(Font(R.font.inter_semi_bold)),
        h0: TextStyle = TextStyle(fontSize = 32.sp),
        h1: TextStyle = TextStyle(fontSize = 28.sp),
        h2: TextStyle = TextStyle(fontSize = 24.sp),
        h3: TextStyle = TextStyle(fontSize = 20.sp),
        h4: TextStyle = TextStyle(fontSize = 16.sp),
        h5: TextStyle = TextStyle(fontSize = 12.sp)
    ) : this(
        h0 = h0.changeStyle(regularFont),
        h0_M = h0.changeStyle(mediumFont),
        h0_B = h0.changeStyle(boldFont),
        h1 = h1.changeStyle(regularFont),
        h1_M = h1.changeStyle(mediumFont),
        h1_B = h1.changeStyle(boldFont),
        h2 = h2.changeStyle(regularFont),
        h2_M = h2.changeStyle(mediumFont),
        h2_B = h2.changeStyle(boldFont),
        h3 = h3.changeStyle(regularFont),
        h3_M = h3.changeStyle(mediumFont),
        h3_B = h3.changeStyle(boldFont),
        h4 = h4.changeStyle(regularFont),
        h4_M = h4.changeStyle(mediumFont),
        h4_B = h4.changeStyle(boldFont),
        h5 = h5.changeStyle(regularFont),
        h5_M = h5.changeStyle(mediumFont),
        h5_B = h5.changeStyle(boldFont)
    )
}
