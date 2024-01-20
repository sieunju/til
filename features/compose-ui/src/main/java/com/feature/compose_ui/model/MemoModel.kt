package com.feature.compose_ui.model

import java.util.Date

/**
 * Description : Compose Memo UiModel
 * 2024-01-20T11:06:21.740Z
 * Created by juhongmin on 1/20/24
 */
data class MemoModel(
    val id: Int,
    val tag: Int,
    val title: String,
    val contents: String,
    val registerDate: Date,
    val imageUrl: String? = null,
    val imageName: String? = null
) {
    constructor(
        memo: MemoEntity,
        file: FileEntity?
    ) : this(
        id = memo.id,
        tag = memo.tag,
        title = memo.title,
        contents = memo.contents,
        registerDate = memo.getDate() ?: Date(),
        imageUrl = file?.imageUrl,
        imageName = file?.originalName
    )
}
