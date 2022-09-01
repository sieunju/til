package com.hmju.core.model.test

import kotlinx.serialization.Serializable

@Serializable
data class TestEntity(
    val integer: Long = 0,
    val str: String = ""
)