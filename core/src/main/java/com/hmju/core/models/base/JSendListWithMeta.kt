package com.hmju.core.models.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Description : JSend JSON
 * {
 *  "status" : true or false,
 *  "message" : String (에러인경우 사용자에게 표시하는 에러 메시지),
 *  "data" : {
 *      "payload" : [],
 *      "meta" : {
 *          "pageSize" : Integer
 *      }
 *  }
 * }
 *
 * Created by juhongmin on 2022/05/15
 */
@Serializable
data class JSendListWithMeta<T : Any, M : MetaEntity>(
    @SerialName("data")
    private val depthData: Payload<T, M>? = null
) : BaseJSend() {
    @Serializable
    data class Payload<T : Any, M : MetaEntity>(
        @SerialName("payload")
        val list: List<T> = listOf(),
        @SerialName("meta")
        val meta: M? = null
    )

    override val isValid: Boolean get() = depthData != null

    val list: List<T>
        get() = depthData?.list ?: listOf()
    val meta: M?
        get() = depthData?.meta
}
