package com.hmju.core.models.base

import kotlinx.serialization.Serializable

@Serializable
open class MetaDTO {
    val limitSize: Int = -1
    val notice: String = ""
}
