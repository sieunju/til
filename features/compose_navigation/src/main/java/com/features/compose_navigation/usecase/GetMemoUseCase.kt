package com.features.compose_navigation.usecase

import com.features.compose_navigation.ApiService
import com.features.compose_navigation.models.MemoUiModel
import com.hmju.core.models.base.JSendList
import com.hmju.core.models.base.getOrDefault
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.network.NetworkExtensions.zip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 5/6/24
 */
class GetMemoUseCase @Inject constructor(
    private val apiService: ApiService
) {
    operator fun invoke(): Flow<List<MemoUiModel>> {
        return flow {
            val queryMap = PagingQueryParams()
            zip(
                { apiService.fetchMemo(queryMap.getQueryMap()) },
                { apiService.fetchUpload(queryMap.getQueryMap()) }
            ) { memoRes, fileRes ->
                val fileList = fileRes.list.filter { it.mimeType.startsWith("image") }
                memoRes.list.mapIndexed { idx, entity ->
                    MemoUiModel.Item(entity, fileList.getOrNull(idx))
                }
            }.onSuccess { emit(it) }.onFailure { error(it) }
        }
    }
}