package com.features.compose_navigation.models

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.features.compose_navigation.models.entity.FileEntity
import com.features.compose_navigation.models.entity.MemoEntity
import com.hmju.core.compose.BaseListClickEvent
import com.hmju.core.compose.BaseUiModel
import com.hmju.core.compose.TilComponent
import com.hmju.core.compose.TilTheme

/**
 * Description : 메모 리스트 UiModel
 *
 * Created by juhongmin on 5/6/24
 */
sealed interface MemoUiModel : BaseUiModel {

    data class Item(
        val id: Int,
        val title: String,
        val contents: String,
        val imagePath: String? = null
    ) : MemoUiModel {

        constructor(
            memoEntity: MemoEntity,
            fileEntity: FileEntity?
        ) : this(
            id = memoEntity.id,
            title = memoEntity.title,
            contents = memoEntity.contents,
            imagePath = fileEntity?.imageUrl
        )

        override fun getType(): String {
            return "ItemType"
        }

        @Composable
        override fun GetUi(clickEvent: (BaseListClickEvent) -> Unit) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(horizontal = 20.dp, vertical = 20.dp))
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    TilComponent.ImageLoader(
                        imageUrl = imagePath ?: "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                    Text(
                        text = title,
                        style = TilTheme.text.h3_B,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                    )

                    Text(
                        text = contents,
                        style = TilTheme.text.h4_M,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                    )
                }
            }
        }
    }

    object Empty : MemoUiModel {
        override fun getType(): String {
            return "Empty"
        }

        @Composable
        override fun GetUi(clickEvent: (BaseListClickEvent) -> Unit) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(TilTheme.color.gray3)
            )
        }
    }
}
