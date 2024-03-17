package com.features.compose_ui.models.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Description :
 *
 * Created by juhongmin on 1/20/24
 */
@Serializable
data class FileEntity(
    val id: Int = 0,
    @SerialName("original_name")
    val originalName: String = "",
    @SerialName("path")
    val imageUrl: String = "",
    @SerialName("mime_type")
    val mimeType: String = ""
)
