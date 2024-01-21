package com.feature.compose_ui.model

import android.graphics.drawable.GradientDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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

    data class Date(
        val dateText: String
    ) : MemoUiModel {
        override fun getType(): String {
            return "MemoDate"
        }

        @Composable
        override fun GetUi() {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(10.dp)
            ) {
                val (date, iv) = createRefs()
                Text(
                    text = dateText,
                    style = TilTheme.text.h3,
                    modifier = Modifier
                        .wrapContentSize()
                        .constrainAs(date) {
                            start.linkTo(parent.start)
                        }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_right),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .constrainAs(iv) {
                            end.linkTo(parent.end)
                        }
                )
            }
        }

        constructor(model: MemoModel) : this(
            dateText = model.getDateText()
        )
    }

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
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, bottom = 15.dp, start = 15.dp),
                    textAlign = TextAlign.Left,
                    style = TilTheme.text.h3_B,
                    maxLines = 1,
                    color = TilTheme.color.gray2
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

    data class ImageAndInfo(
        val imageUrl: String,
        val title: String,
        val contents: String
    ) : MemoUiModel {
        override fun getType(): String {
            return "ImageAndInfo"
        }

        @OptIn(ExperimentalGlideComposeApi::class)
        @Composable
        override fun GetUi() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 15.dp)
            ) {
                GlideImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    contentScale = ContentScale.Crop,
                    loading = placeholder(
                        GradientDrawable(
                            GradientDrawable.Orientation.BL_TR,
                            intArrayOf(R.color.gray3, R.color.gray3)
                        )
                    ),
                    failure = placeholder(R.drawable.ic_error)
                )

                Column(
                    modifier = Modifier
                        .weight(1F)
                        .padding(start = 15.dp, end = 15.dp)
                        .wrapContentHeight()
                ) {

                    Text(
                        text = title,
                        maxLines = 1,
                        textAlign = TextAlign.Left,
                        style = TilTheme.text.h4_B,
                        color = TilTheme.color.black
                    )

                    Text(
                        text = contents,
                        maxLines = 3,
                        textAlign = TextAlign.Left,
                        style = TilTheme.text.h5,
                        color = TilTheme.color.black
                    )
                }
            }
        }

        constructor(model: MemoModel) : this(
            imageUrl = model.imageUrl ?: "",
            title = model.title,
            contents = model.contents
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
                    .height(50.dp)
                    .padding(bottom = 10.dp)
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

    object Divider1 : MemoUiModel {
        override fun getType(): String {
            return "Divider1"
        }

        @Composable
        override fun GetUi() {
            Divider(color = TilTheme.color.gray1, thickness = 1.dp)
        }
    }

    object Divider10 : MemoUiModel {
        override fun getType(): String {
            return "Divider10"
        }

        @Composable
        override fun GetUi() {
            Divider(color = TilTheme.color.gray1, thickness = 10.dp)
        }
    }
}
