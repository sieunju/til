package com.til.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Description : 에러 헨들링 API 서비스
 *
 * Created by juhongmin on 2022/05/12
 */
interface ErrorHandlingApiService {
    @GET("/api/error/505")
    fun getError505(): Single<String>

    @POST("/api/error/505")
    fun postError505(): Single<String>

    @GET("/api/error/404")
    fun getError404(): Single<String>

    @POST("/api/error/404")
    fun postError404(): Single<String>
}
