package com.features.compose_ui.usecase

import com.features.compose_ui.ApiService
import com.features.compose_ui.models.entity.FileEntity
import com.features.compose_ui.models.entity.MemoEntity
import com.features.compose_ui.models.MemoModel
import com.hmju.core.models.base.JSendList
import com.hmju.core.models.base.getOrDefault
import com.hmju.core.models.params.PagingParameter
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
        query: PagingParameter,
        scope: CoroutineScope
    ): List<MemoModel> {
        val memoWork = scope.async { reqMemoList(query) }
        val imageWork = scope.async { reqImageList(query) }
        return getMemoModel(memoWork.await(), imageWork.await())
    }

    private suspend fun reqMemoList(query: PagingParameter): List<MemoEntity> {
        return apiService.fetchMemo(query.getQueryMap())
            .getOrDefault(JSendList())
            .payload
    }

    private suspend fun reqImageList(query: PagingParameter): List<FileEntity> {
        return apiService.fetchUpload(query.getQueryMap())
            .getOrDefault(JSendList())
            .payload
            .filter { it.mimeType.startsWith("image") }
    }

    private fun getMemoModel(
        memoList: List<MemoEntity>,
        uploadList: List<FileEntity>
    ): List<MemoModel> {
        return memoList
            .mapIndexed { idx, entity ->
                MemoModel(entity, uploadList.getOrNull(idx))
            }
    }
}