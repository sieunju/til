package com.features.compose_navigation.models

/**
 * Description :
 *
 * Created by juhongmin on 5/6/24
 */
sealed class MemoClickEvent {

    object Init : MemoClickEvent()

    data class Item(
        val msg: String
    )
}
