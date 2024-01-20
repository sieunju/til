package com.feature.compose_ui.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Description :
 *
 * Created by juhongmin on 1/20/24
 */
@Serializable
data class MemoEntity(
    val id: Int = 0,
    val userId: String = "",
    val tag: Int = 0,
    val title: String = "",
    val contents: String = "",
    @SerialName("register_date")
    val registerDate: String = ""
) {
}
