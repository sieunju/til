package com.hmju.core.models.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Description : JSend JSON
 * {
 *  "status" : true or false,
 *  "message" : String (에러인경우 사용자에게 표시하는 에러 메시지),
 *  "list" : []
 * }
 *
 * Created by juhongmin on 09/01/22
 */
@Serializable
data class JSendList<T : Any>(
    @SerialName("list")
    val list: List<T> = listOf()
) : BaseJSend() {
    override val isValid: Boolean get() = isSuccess
}
