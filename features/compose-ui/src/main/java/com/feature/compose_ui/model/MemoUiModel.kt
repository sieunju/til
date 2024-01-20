package com.feature.compose_ui.model

import android.graphics.drawable.GradientDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.feature.compose_ui.TilTheme
import com.hmju.core.R

/**
 * Description : Compose Memo UiModel
 *
 * Created by juhongmin on 1/20/24
 */
sealed interface MemoUiModel {
    fun getType(): String

    @Composable
    fun GetUi()

    data class Title(
        val title: String
    ) : MemoUiModel {
        override fun getType(): String {
            return "Title"
        }

        constructor(
            entity: MemoModel
        ) : this(
            title = entity.title
        )

        @Composable
        override fun GetUi() {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(TilTheme.color.black)
                        .padding(top = 15.dp, bottom = 15.dp, start = 15.dp),
                    textAlign = TextAlign.Left,
                    style = TilTheme.text.h3_B,
                    maxLines = 1,
                    color = TilTheme.color.white
                )
            }
        }
    }

    data class TagGrayColor(
        val model: MemoModel
    ) : MemoUiModel {
        override fun getType(): String {
            return "TagGrayColor"
        }

        @Composable
        override fun GetUi() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TilTheme.color.gray2)
            ) {
                Text(
                    text = model.title,
                    color = TilTheme.color.white,
                    style = TilTheme.text.h3_M
                )
                Text(
                    text = model.imageName ?: "NotImage...",
                    color = TilTheme.color.white,
                    style = TilTheme.text.h3_M
                )
            }
        }
    }

    data class TagBlueColor(
        val model: MemoModel
    ) : MemoUiModel {
        override fun getType(): String {
            return "TagGrayColor"
        }

        @Composable
        override fun GetUi() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TilTheme.color.blue)
            ) {
                Text(
                    text = model.title,
                    color = TilTheme.color.white,
                    style = TilTheme.text.h3_M
                )
                Text(
                    text = model.imageName ?: "NotImage...",
                    color = TilTheme.color.white,
                    style = TilTheme.text.h3_M
                )
            }
        }
    }

    data class Contents(
        val contents: String
    ) : MemoUiModel {
        override fun getType(): String {
            return "Contents"
        }

        @Composable
        override fun GetUi() {
            Text(
                text = contents,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(TilTheme.color.white)
                    .padding(top = 15.dp, bottom = 15.dp, start = 15.dp),
                textAlign = TextAlign.Left,
                style = TilTheme.text.h5,
                color = TilTheme.color.black
            )
        }

        constructor(
            entity: MemoModel
        ) : this(
            contents = entity.contents
        )
    }

    data class ImageThumb(
        val imageUrl: String
    ) : MemoUiModel {
        override fun getType(): String {
            return "ImageThumb"
        }

        @ExperimentalGlideComposeApi
        @Composable
        override fun GetUi() {
            GlideImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(1.dp, TilTheme.color.gray3),
                contentScale = ContentScale.Crop,
                loading = placeholder(
                    GradientDrawable(
                        GradientDrawable.Orientation.BL_TR,
                        intArrayOf(R.color.gray3, R.color.gray3)
                    )
                ),
                failure = placeholder(R.drawable.ic_error)
            )
        }
    }

    data class Buttons(
        val model: MemoModel
    ) : MemoUiModel {
        override fun getType(): String {
            return "Buttons"
        }

        @Composable
        override fun GetUi() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
                    .height(50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(TilTheme.color.yellow)
                        .fillMaxHeight()
                        .weight(1F),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Button1",
                        textAlign = TextAlign.Center
                    )
                }

                Box(
                    modifier = Modifier
                        .background(TilTheme.color.gray3Light)
                        .fillMaxHeight()
                        .weight(1F),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "OK",
                        textAlign = TextAlign.Center
                    )
                }

                Box(
                    modifier = Modifier
                        .background(TilTheme.color.red)
                        .fillMaxHeight()
                        .weight(1F),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Cancel",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    object MemoDivider : MemoUiModel {
        override fun getType(): String {
            return "Divider"
        }

        @Composable
        override fun GetUi() {
            Divider(
                color = TilTheme.color.white,
                thickness = 20.dp
            )
        }
    }
}
