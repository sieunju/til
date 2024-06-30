package com.features.network_jsend_format

import com.features.network_jsend_format.models.entity.JSendTestEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendList
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.JSendObj
import com.hmju.core.models.base.JSendObjWithMeta
import com.hmju.core.models.base.MetaEntity
import retrofit2.http.GET

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
 */
interface ApiService {
    @GET("/api/v1/til/jsend/meta")
    suspend fun fetchJSendWithMeta(): ApiResponse<JSendObjWithMeta<String, MetaEntity>>

    @GET("/api/v1/til/jsend")
    suspend fun fetchJSend(): ApiResponse<JSendObj<JSendTestEntity>>

    @GET("/api/v1/til/jsend/list/meta")
    suspend fun fetchJSendListWithMeta(): ApiResponse<JSendListWithMeta<String, MetaEntity>>

    @GET("/api/v1/til/jsend/list")
    suspend fun fetchJSendList(): ApiResponse<JSendList<String>>
}
