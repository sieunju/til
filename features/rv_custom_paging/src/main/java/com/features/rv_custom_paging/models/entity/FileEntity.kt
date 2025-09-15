package com.features.rv_custom_paging.models.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
