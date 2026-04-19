package com.features.network_error_handling.models.entity

import com.hmju.core.models.base.BaseJSend
import kotlinx.serialization.Serializable

@Serializable
data class JSendTestEntity(
    val id: String = ""
) : BaseJSend()
