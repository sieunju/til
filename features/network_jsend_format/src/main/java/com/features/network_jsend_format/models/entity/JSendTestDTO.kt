package com.features.network_jsend_format.models.entity

import com.hmju.core.models.base.BaseJSend
import kotlinx.serialization.Serializable

@Serializable
data class JSendTestDTO(
    val id: String = ""
) : BaseJSend()
