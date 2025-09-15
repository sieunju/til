package com.features.network_error_handling

import com.features.network_error_handling.models.entity.JSendTestEntity
import com.features.network_error_handling.models.meta.CustomMetaEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.JSendObj
import com.hmju.core.models.base.JSendObjWithMeta
import com.hmju.core.models.base.MetaEntity
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Description : Network Error Handling API Service
 *
 * Created by juhongmin on 2/25/24
 */
interface ApiService {
    @GET("/api/v1/til/jsend")
    suspend fun fetchJSend(): ApiResponse<JSendObj<JSendTestEntity>>

    @GET("/api/v1/til/jsend/list/meta")
    suspend fun fetchJSendListWithMeta(): ApiResponse<JSendListWithMeta<String, MetaEntity>>

    @POST("/api/v1/til/error/505")
    suspend fun postError505(): ApiResponse<String>

    @GET("/api/v1/til/error/404")
    suspend fun getError404(): ApiResponse<String>

    @POST("/api/v1/til/error/404")
    suspend fun postError404(): ApiResponse<String>

    @GET("/api/v1/til/jsend/meta")
    suspend fun fetchJSendWithMeta(): ApiResponse<JSendObjWithMeta<JSendTestEntity, CustomMetaEntity>>
}
