package com.hmju.core.compose

import androidx.compose.runtime.Composable

/**
 * Description : Compose LazyColumn 전용 UiModel
 *
 * Created by juhongmin on 6/2/24
 */
interface BaseUiModel {
    fun getType(): String

    @Composable
    fun GetUi(clickEvent: (BaseListClickEvent) -> Unit)
}
