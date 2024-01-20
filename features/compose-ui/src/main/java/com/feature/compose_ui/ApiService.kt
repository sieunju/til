package com.feature.compose_ui

import com.feature.compose_ui.model.FileEntity
import com.feature.compose_ui.model.MemoEntity
import com.hmju.core.model.base.ApiResponse
import com.hmju.core.model.base.JSendList
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Description :
 *
 * Created by juhongmin on 1/20/24
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
