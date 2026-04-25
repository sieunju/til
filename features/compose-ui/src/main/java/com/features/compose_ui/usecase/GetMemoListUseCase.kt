package com.features.compose_ui.usecase

import com.features.compose_ui.ApiService
import com.features.compose_ui.models.entity.FileDTO
import com.features.compose_ui.models.entity.MemoDTO
import com.features.compose_ui.models.Memo
import com.hmju.core.models.base.JSendList
import com.hmju.core.models.base.getOrDefault
import com.hmju.core.models.params.PagingQueryParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 1/20/24
 */
class GetMemoListUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(
        query: PagingQueryParams,
        scope: CoroutineScope
    ): List<Memo> {
        val memoWork = scope.async { reqMemoList(query) }
        val imageWork = scope.async { reqImageList(query) }
        return getMemo(memoWork.await(), imageWork.await())
    }

    private suspend fun reqMemoList(query: PagingQueryParams): List<MemoDTO> {
        return apiService.fetchMemo(query.getQueryMap())
            .getOrDefault(JSendList())
            .list
    }

    private suspend fun reqImageList(query: PagingQueryParams): List<FileDTO> {
        return apiService.fetchUpload(query.getQueryMap())
            .getOrDefault(JSendList())
            .list
            .filter { it.mimeType.startsWith("image") }
    }

    private fun getMemo(
        memoList: List<MemoDTO>,
        uploadList: List<FileDTO>
    ): List<Memo> {
        return memoList
            .mapIndexed { idx, entity ->
                Memo(entity, uploadList.getOrNull(idx))
            }
    }
}
