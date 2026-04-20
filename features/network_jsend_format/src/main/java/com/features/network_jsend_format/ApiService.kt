package com.features.network_jsend_format

import com.features.network_jsend_format.models.entity.JSendTestDTO
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendList
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.MetaDTO
import retrofit2.http.GET

/**
 * Description :
 *
 * Created by juhongmin on 2/26/24
 */
interface ApiService {
    @GET("/api/v1/til/jsend/meta")
    suspend fun fetchJSendWithMeta(): ApiResponse<JSendTestDTO>

    @GET("/api/v1/til/jsend")
    suspend fun fetchJSend(): ApiResponse<JSendTestDTO>

    @GET("/api/v1/til/jsend/list/meta")
    suspend fun fetchJSendListWithMeta(): ApiResponse<JSendListWithMeta<String, MetaDTO>>

    @GET("/api/v1/til/jsend/list")
    suspend fun fetchJSendList(): ApiResponse<JSendList<String>>
}
