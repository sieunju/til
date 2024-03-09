package com.features.rv_refactor_diff_util.models.meta

import com.hmju.core.models.base.MetaEntity
import kotlinx.serialization.Serializable

@Serializable
data class CustomMetaEntity(
    val metaSize: Int = 0,
    val customPage: Int = 0
) : MetaEntity()
