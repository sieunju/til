package com.hmju.core.model.meta

import com.hmju.core.model.base.MetaEntity
import kotlinx.serialization.Serializable

/**
 * Description :
 *
 * Created by juhongmin on 2022/01/25
 */
@Serializable
data class CustomMetaEntity(
    val metaSize : Int = 0,
    val customPage: Int = 0
) : MetaEntity()