package com.features.compose_navigation.models

import androidx.compose.runtime.Composable
import com.features.compose_navigation.models.entity.FileEntity
import com.features.compose_navigation.models.entity.MemoEntity

/**
 * Description : 메모 리스트 UiModel
 *
 * Created by juhongmin on 5/6/24
 */
sealed interface MemoUiModel {
    fun getType(): String

    @Composable
    fun GetUi(clickEvent: (MemoClickEvent) -> Unit)

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
        override fun GetUi(clickEvent: (MemoClickEvent) -> Unit) {

        }
    }
}
