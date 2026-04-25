package com.features.network_v2.models.entity

import com.hmju.core.models.base.BaseJSend
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JSendDTO(
    @SerialName("id")
    val id: String = ""
) : BaseJSend()
