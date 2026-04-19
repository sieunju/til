package com.hmju.core.models.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Description : JSend JSON
 * {
 *  "status" : true or false,
 *  "message" : String (에러인경우 사용자에게 표시하는 에러 메시지),
 *  "list" : [],
 *  "meta" : {
 *      "pageSize" : Integer
 *  }
 * }
 *
 * Created by juhongmin on 2022/05/15
 */
@Serializable
data class JSendListWithMeta<T : Any, M : MetaEntity>(
    @SerialName("list")
    val list: List<T> = listOf(),
    @SerialName("meta")
    val meta: M? = null
) : BaseJSend() {
    override val isValid: Boolean get() = isSuccess
}
