package com.features.network_v2.models.body

import kotlinx.serialization.Serializable

@Serializable
data class ErrorBody(
    val status: Boolean = false,
    val message: Message = Message(),
) {
    @Serializable
    data class Message(
        val name: String = "",
        val contents: String = "",
    )
}