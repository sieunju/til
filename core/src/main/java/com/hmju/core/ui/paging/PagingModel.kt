package com.hmju.core.ui.paging

/**
 * Description : 페이징 처리 관련 데이터 모델
 * @param isLoading 로드 API 중인지 유무
 * @param isLast 더이상 페이징처리를 할수 없는 상태인지 유무 Flag
 * Created by juhongmin on 2022/01/21
 */
data class PagingModel(
    var isLoading: Boolean = true,
    var isLast: Boolean = false
) {
    /**
     * 초기에 API 호출 방지를 위한 파라미터 초기화 함수
     */
    fun initParams() {
        isLoading = true
        isLast = false
    }

    fun hasMore(): Boolean {
        return !isLast && !isLoading
    }
}
