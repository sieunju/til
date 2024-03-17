package com.features.base_mvvm_lifecycle_bridge

import java.io.Serializable

/**
 * Description :
 *
 * Created by juhongmin on 3/14/24
 */
data class SerializableEntity(
    val title: String,
    val uid: Long
) : Serializable