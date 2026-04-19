package com.features.network_expired_token.models.entity

import com.hmju.core.models.base.BaseJSend
import kotlinx.serialization.Serializable

@Serializable
data class JSendTestEntity(
    val id: String = ""
) : BaseJSend()
