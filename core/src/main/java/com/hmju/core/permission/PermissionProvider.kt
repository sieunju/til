package com.hmju.core.permission

/**
 * Description : Permission Provider
 *
 * Created by juhongmin on 4/5/24
 */
interface PermissionProvider {

    fun isGrated(permission: String): Boolean
    fun isAllGrated(list: List<String>): Boolean
    fun isAllGrated(map: Map<String,Boolean>) : Boolean
    fun moveToSetting()
}
