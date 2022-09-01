package com.hmju.core.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JSendList<T : Any>(
    @SerialName("data")
    private val depthData: Payload<T>? = null
) : BaseJSend() {
    @Serializable
    data class Payload<T : Any>(
        @SerialName("payload")
        val list: List<T> = listOf()
    )

    val isValid: Boolean
        get() = depthData != null

    val payload: List<T>
        get() = depthData?.list ?: listOf()
}
