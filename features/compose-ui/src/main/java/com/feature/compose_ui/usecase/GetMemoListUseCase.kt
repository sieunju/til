package com.feature.compose_ui.usecase

import com.feature.compose_ui.ApiService
import com.feature.compose_ui.model.MemoEntity
import com.hmju.core.model.base.JSendListWithMeta
import com.hmju.core.model.base.getOrDefault
import com.hmju.core.model.params.MemoParameter
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 1/20/24
 */
class GetMemoListUseCase @Inject constructor(
    private val apiService: ApiService
) {
    suspend operator fun invoke(query: MemoParameter): List<MemoEntity> {
        return apiService.fetchMemo(query.getQueryParameter())
            .getOrDefault(JSendListWithMeta())
            .payload
    }
}
