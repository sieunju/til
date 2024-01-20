package com.feature.compose_ui

import com.feature.compose_ui.model.MemoEntity
import com.hmju.core.model.base.ApiResponse
import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.base.MetaEntity
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
    ): ApiResponse<JSendListWithMeta<MemoEntity, MetaEntity>>
}
