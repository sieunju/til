package com.features.network

import com.hmju.core.model.auth.AuthTokenEntity
import com.hmju.core.model.base.*
import com.hmju.core.model.meta.CustomMetaEntity
import com.hmju.core.model.test.JSendTestEntity
import com.hmju.core.model.test.TestEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Description : Network Feature 에 필요한 API Service 정의
 *
 * Created by juhongmin on 2023/03/27
 */
interface ApiService {
    @POST("/api/til/auth/refresh")
    fun postTokenRefresh(): Single<JSendObj<AuthTokenEntity>>

    @POST("/api/til/auth/expired")
    fun postTokenExpired(): Single<JSendObj<AuthTokenEntity>>

    @GET("/api/til/jsend/error/test")
    fun fetchErrorTest(): Single<JSendObjWithMeta<String, MetaEntity>>

    @GET("/api/til/jsend/list")
    fun fetchJSendList(): Single<JSendList<String>>

    @GET("/api/til/jsend/list/meta")
    fun fetchJSendListWithMeta(): Single<JSendListWithMeta<String, MetaEntity>>

    @GET("/api/til/jsend")
    fun fetchJSend(): Single<JSendObj<JSendTestEntity>>

    @GET("/api/til/jsend/meta")
    fun fetchJSendWithMeta(): Single<JSendObjWithMeta<JSendTestEntity, CustomMetaEntity>>

    @GET("/api/til/test")
    fun fetchTest(): Single<JSendObj<TestEntity>>

    @GET("/api/til/error/505")
    fun getError505(): Single<String>

    @POST("/api/til//error/505")
    fun postError505(): Single<String>

    @GET("/api/til/error/404")
    fun getError404(): Single<String>

    @POST("/api/til/error/404")
    fun postError404(): Single<String>
}
