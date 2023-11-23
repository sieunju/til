package com.hmju.core.model.params

import java.net.URLEncoder

data class GoodsParameter(
    var pageNo: Int = 1,
    var pageSize: Int = 25,
    var tempQueryList: List<String>? = null,
    var tempQueryString: List<String>? = null
) {

    fun getQueryParameter(): Map<String, Any> {
        val map = hashMapOf<String, Any>()
        map["pageNo"] = pageNo.toString()
        map["pageSize"] = pageSize.toString()
        tempQueryList?.let { map.put("tempList", it) }
        tempQueryString?.let {
            val list = mutableListOf<String>()
            it.forEach { str ->
                list.add(URLEncoder.encode(str, Charsets.UTF_8.name()))
            }
            map.put("tempStr", list.joinToString(","))
        }
        return map
    }
}
