package com.features.network_v2.model

import kotlinx.serialization.Serializable

/**
 * Description :
 *
 * Created by juhongmin on 11/24/23
 */
@Serializable
data class TokenBody(
    val email: String
)