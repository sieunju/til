package com.features.rv_refactor_diff_util.models.meta

import com.hmju.core.models.base.MetaDTO
import kotlinx.serialization.Serializable

@Serializable
data class CustomMetaDTO(
    val metaSize: Int = 0,
    val customPage: Int = 0
) : MetaDTO()
