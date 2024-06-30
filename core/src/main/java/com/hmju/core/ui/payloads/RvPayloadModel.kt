package com.hmju.core.ui.payloads

import com.hmju.core.ui.base.BaseUiModel

/**
 * Description : RecyclerView Payload Update 전용 데이터 모델
 *
 * Created by juhongmin on 2024. 6. 30.
 */
data class RvPayloadModel(
    var firstPos: Int = -1,
    var lastPos: Int = -1,
    val list: MutableList<BaseUiModel> = mutableListOf()
) {

    fun isValidate(): Boolean {
        return list.isNotEmpty()
    }

    fun isRangeValidate(): Boolean {
        return firstPos != -1 && lastPos != -1
    }

    /**
     * 업데이트 리스트의 최소 / 최대 범위를 찾아서 업데이트할 정보를 셋팅하는 함수
     * 단, 인자값으로 넣는 리스트는 data class 이거나, CurrentList 에 있는 데이터 그대로
     * payloadList 에 추가만 하도록 해야함 [Collection.indexOf] 내부 로직 때문
     * 성능 N X M
     * LiveData 를 사용하는 경우 setValue 를 해줘야 합니다. AKA. notifyObserver
     * @param list Current List
     * @param payloadList Update List
     */
    fun update(list: List<BaseUiModel>, payloadList: List<BaseUiModel>) {
        if (list.isEmpty() || payloadList.isEmpty()) return
        var firstPos = -1
        var lastPos = 1
        payloadList.forEach {
            val findIndex = list.indexOf(it)
            if (findIndex == -1) return@forEach
            firstPos = if (firstPos == -1) {
                findIndex
            } else {
                firstPos.coerceAtMost(findIndex)
            }
            lastPos = lastPos.coerceAtLeast(findIndex)
        }
        this.firstPos = firstPos
        this.lastPos = lastPos.plus(1)
        this.list.clear()
        this.list.addAll(payloadList)
    }
}
