package com.features.async_migrate.models.meta

import com.hmju.core.models.base.MetaEntity
import kotlinx.serialization.Serializable

@Serializable
data class CustomMetaEntity(
    val metaSize : Int = 0,
    val customPage: Int = 0
) : MetaEntity()
