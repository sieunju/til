package com.features.network_v2

import com.features.network_v2.models.entity.GoodsEntity
import com.features.network_v2.models.entity.JSendEntity
import com.features.network_v2.models.entity.JwtTokenTestEntity
import com.features.network_v2.models.meta.CustomMetaEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendListWithMeta
import com.hmju.core.models.base.JSendObj
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 11/22/23
 */
internal interface ApiService {

    @GET("/api/v1/til/error/404")
    suspend fun fetchError404(): ApiResponse<JSendObj<JSendEntity>>

    @GET("/api/v1/til/jsend")
    fun fetchJSendRx(): Single<JSendObj<JSendEntity>>

    @GET("/api/v1/til/goods")
    fun fetchGoodsRx(
        @QueryMap(encoded = true) params: Map<String, String>,
    ): Single<JSendListWithMeta<GoodsEntity, CustomMetaEntity>>

    @GET("/api/v1/til/error/404")
    fun fetchError404Rx(): Single<JSendObj<JSendEntity>>

    @GET("/api/v1/memo/aos")
    fun fetchAndroid(): Single<ResponseBody>

    @GET("/api/v1/til/auth/jwt/test/{delay}")
    fun fetchJwtTest(
        @Path("delay") delay: Int = 0
    ): Single<JSendObj<JwtTokenTestEntity>>

    @GET("/api/v1/til/auth/jwt/test1/{delay}")
    fun fetchJwtTest1(
        @Path("delay") delay: Int = 0
    ): Single<JSendObj<JwtTokenTestEntity>>

    @GET("/api/v1/til/auth/jwt/test2/{delay}")
    fun fetchJwtTest2(
        @Path("delay") delay: Int = 0
    ): Single<JSendObj<JwtTokenTestEntity>>

    @GET("/api/v1/til/auth/jwt/test/{delay}")
    suspend fun fetchJwtTestCo(
        @Path("delay") delay: Int = 0
    ): ApiResponse<JSendObj<JwtTokenTestEntity>>

    @GET("/api/v1/til/auth/jwt/test1/{delay}")
    suspend fun fetchJwtTest1Co(
        @Path("delay") delay: Int = 0
    ): ApiResponse<JSendObj<JwtTokenTestEntity>>

    @GET("/api/v1/til/auth/jwt/test2/{delay}")
    suspend fun fetchJwtTest2Co(
        @Path("delay") delay: Int = 0
    ): ApiResponse<JSendObj<JwtTokenTestEntity>>

}