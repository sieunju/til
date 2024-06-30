package com.features.compose_navigation.models

import com.hmju.core.compose.BaseListClickEvent

/**
 * Description :
 *
 * Created by juhongmin on 5/6/24
 */
sealed class MemoClickEvent : BaseListClickEvent {

    object Init : MemoClickEvent()

    data class Item(
        val msg: String
    )
}
