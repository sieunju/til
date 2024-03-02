package com.hmju.core.network

import com.hmju.core.models.base.JSendObj
import com.hmju.core.models.body.LikeRequestBody
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
interface ApiService {
    @POST("/api/v1/til/goods/like")
    fun postLike(
        @Body body: LikeRequestBody
    ): Single<JSendObj<ResponseBody>>

    @DELETE("/api/v1/til/goods/like/{id}")
    fun deleteLike(
        @Path("id") id: Long
    ): Single<JSendObj<ResponseBody>>
}
