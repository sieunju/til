package com.hmju.core.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Description :
 *
 * Created by juhongmin on 1/21/24
 */
@Suppress("unused")
class TilThemeExample {

    @Composable
    private fun TextStyleExampleItem(text: String, style: TextStyle) {
        Text(
            text = text,
            style = style,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 5.dp)
        )
    }

    @Preview(showBackground = true, backgroundColor = 0xFFFFFF)
    @Composable
    private fun TextStyleExample() {
        Column(
            modifier = Modifier.wrapContentWidth()
        ) {
            Row {
                TextStyleExampleItem("H0", TilTheme.text.h0)
                TextStyleExampleItem("H0_M", TilTheme.text.h0_M)
                TextStyleExampleItem("H0_B", TilTheme.text.h0_B)
            }
            Row {
                TextStyleExampleItem("H1", TilTheme.text.h1)
                TextStyleExampleItem("H1_M", TilTheme.text.h1_M)
                TextStyleExampleItem("H1_B", TilTheme.text.h1_B)
            }
            Row {
                TextStyleExampleItem("H2", TilTheme.text.h2)
                TextStyleExampleItem("H2_M", TilTheme.text.h2_M)
                TextStyleExampleItem("H2_B", TilTheme.text.h2_B)
            }
            Row {
                TextStyleExampleItem("H3", TilTheme.text.h3)
                TextStyleExampleItem("H3_M", TilTheme.text.h3_M)
                TextStyleExampleItem("H3_B", TilTheme.text.h3_B)
            }
            Row {
                TextStyleExampleItem("H4", TilTheme.text.h4)
                TextStyleExampleItem("H4_M", TilTheme.text.h4_M)
                TextStyleExampleItem("H4_B", TilTheme.text.h4_B)
            }
            Row {
                TextStyleExampleItem("H5", TilTheme.text.h5)
                TextStyleExampleItem("H5_M", TilTheme.text.h5_M)
                TextStyleExampleItem("H5_B", TilTheme.text.h5_B)
            }
        }
    }

    @Composable
    private fun ColorStyleExampleItem(
        text: String,
        color: Color
    ) {
        Box(
            modifier = Modifier
                .background(color = color)
                .width(300.dp)
        ) {
            Text(
                text = text,
                style = TilTheme.text.h4_M,
                color = if (text == "white") TilTheme.color.black else TilTheme.color.white
            )
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFFFFF)
    @Composable
    private fun ColorStyleExample() {
        Column(
            modifier = Modifier.wrapContentWidth()
        ) {
            ColorStyleExampleItem("white", TilTheme.color.white)
            ColorStyleExampleItem("black", TilTheme.color.black)
            ColorStyleExampleItem("blue", TilTheme.color.blue)
            ColorStyleExampleItem("red", TilTheme.color.red)
            ColorStyleExampleItem("purple", TilTheme.color.purple)
            ColorStyleExampleItem("yellow", TilTheme.color.yellow)
            ColorStyleExampleItem("gray1", TilTheme.color.gray1)
            ColorStyleExampleItem("gray2", TilTheme.color.gray2)
            ColorStyleExampleItem("gray3", TilTheme.color.gray3)
            ColorStyleExampleItem("gray3Light", TilTheme.color.gray3Light)
            ColorStyleExampleItem("gray4", TilTheme.color.gray4)
            ColorStyleExampleItem("gray5", TilTheme.color.gray5)
        }
    }
}
