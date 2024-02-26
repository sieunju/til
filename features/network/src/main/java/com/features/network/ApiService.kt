package com.features.network

import com.features.network.models.entity.JSendTestEntity
import com.hmju.core.models.base.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * Description : Network Feature 에 필요한 API Service 정의
 *
 * Created by juhongmin on 2023/03/27
 */
interface ApiService {

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
