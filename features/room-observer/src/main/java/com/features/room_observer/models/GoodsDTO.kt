package com.features.room_observer.models

import com.hmju.core.models.base.BaseJSend
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoodsDTO(
    val id: Long = 0,
    val title: String = "",
    val description: String = "",
    @SerialName("image_path")
    val imagePath: String = ""
) : BaseJSend()
