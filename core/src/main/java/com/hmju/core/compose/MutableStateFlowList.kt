package com.hmju.core.compose

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Description : Compose + Flow 전용 List 형태의 Flow
 *
 * Created by juhongmin on 5/6/24
 */
class MutableStateFlowList<T> {

    private val _list = mutableListOf<T>()
    private val _stateFlow = MutableStateFlow<List<T>>(emptyList())

    val stateFlow = _stateFlow.asStateFlow()

    fun add(item: T) {
        _list.add(item)
        notifyObserver()
    }

    fun addAll(items: List<T>?) {
        if (items == null) return
        _list.addAll(items)
        notifyObserver()
    }

    fun remove(item: T) {
        _list.remove(item)
        notifyObserver()
    }

    private fun notifyObserver() {
        _stateFlow.value = _list.toList()
    }
}
