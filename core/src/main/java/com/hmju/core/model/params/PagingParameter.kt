package com.hmju.core.model.params

/**
 * Description :
 *
 * Created by juhongmin on 1/20/24
 */
data class PagingParameter(
    var pageNo: Int = 1,
    var pageSize: Int = 26
) {

    fun getQueryMap(): Map<String, String> {
        val map = hashMapOf<String, String>()
        map["pageNo"] = pageNo.toString()
        map["pageSize"] = pageSize.toString()
        return map
    }
}
