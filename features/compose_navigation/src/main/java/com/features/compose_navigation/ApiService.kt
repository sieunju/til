package com.features.compose_navigation

import com.features.compose_navigation.models.entity.FileEntity
import com.features.compose_navigation.models.entity.MemoEntity
import com.hmju.core.models.base.ApiResponse
import com.hmju.core.models.base.JSendList
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 5/6/24
 */
interface ApiService {
    @GET("/api/v1/memo")
    suspend fun fetchMemo(
        @QueryMap(encoded = true) params: Map<String, String>
    ): ApiResponse<JSendList<MemoEntity>>

    @GET("/api/v1/uploads")
    suspend fun fetchUpload(
        @QueryMap(encoded = true) params: Map<String, String>
    ): ApiResponse<JSendList<FileEntity>>
}
