package com.hmju.core.models.params

/**
 * Description : Pagination Parameter
 *
 * Created by juhongmin on 1/20/24
 */
open class PagingQueryParams(
    open var pageNo: Int = 1,
    open var pageSize: Int = 26
) {

    open fun getQueryMap(): Map<String, String> {
        val map = hashMapOf<String, String>()
        map["pageNo"] = pageNo.toString()
        map["pageSize"] = pageSize.toString()
        return map
    }

    override fun toString(): String {
        return "pageNo=${pageNo}, pageSize=${pageSize}"
    }
}
