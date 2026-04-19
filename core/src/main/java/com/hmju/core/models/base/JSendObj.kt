package com.hmju.core.models.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Description : JSend JSON (flat format)
 * {
 *  "status" : true or false,
 *  "message" : String (에러인경우 사용자에게 표시하는 에러 메시지),
 *  ...T fields...   <- payload 없이 T 의 필드가 루트에 직접 병합
 * }
 *
 * Created by juhongmin on 2022/05/15
 */
@Serializable
data class JSendObj<T : Any>(
    @SerialName("obj")
    private val _obj: T? = null
) : BaseJSend() {

    override val isValid: Boolean get() = _obj != null

    val obj: T
        get() = _obj ?: throw NullPointerException("Data is Null")
}
