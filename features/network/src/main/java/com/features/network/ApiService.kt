package com.features.network

import com.features.network.models.JSendTestEntity
import com.hmju.core.model.base.*
import com.hmju.core.model.meta.CustomMetaEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Description : Network Feature 에 필요한 API Service 정의
 *
 * Created by juhongmin on 2023/03/27
 */
interface ApiService {

    @GET("/api/v1/til/jsend/list/meta")
    suspend fun fetchJSendListWithMeta(): ApiResponse<JSendListWithMeta<String, MetaEntity>>

    @GET("/api/v1/til/jsend")
    suspend fun fetchJSend(): ApiResponse<JSendObj<JSendTestEntity>>

    @GET("/api/v1/til/jsend/meta")
    suspend fun fetchJSendWithMeta(): ApiResponse<JSendObjWithMeta<JSendTestEntity, CustomMetaEntity>>

    @POST("/api/v1/til/error/505")
    suspend fun postError505(): ApiResponse<String>

    @GET("/api/v1/til/error/404")
    suspend fun getError404(): ApiResponse<String>

    @POST("/api/v1/til/error/404")
    suspend fun postError404(): ApiResponse<String>

    @GET("/api/v1/til/jsend/meta")
    fun fetchJSendWithMetaRx(): Single<JSendObjWithMeta<JSendTestEntity, MetaEntity>>

    @GET("/api/v1/til/jsend")
    fun fetchJSendRx(): Single<JSendObj<JSendTestEntity>>

    @GET("/api/v1/til/jsend/list/meta")
    fun fetchJSendListWithMetaRx(): Single<JSendListWithMeta<String, MetaEntity>>

    @GET("/api/v1/til/jsend/list")
    fun fetchJSendListRx(): Single<JSendList<String>>

    @GET("/api/v1/til/jsend/error/test")
    fun fetchErrorTestRx(): Single<JSendObjWithMeta<String, MetaEntity>>

}
