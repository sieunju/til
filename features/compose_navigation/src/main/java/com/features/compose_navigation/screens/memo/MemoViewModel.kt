package com.features.compose_navigation.screens.memo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.features.compose_navigation.models.MemoClickEvent
import com.features.compose_navigation.models.MemoUiModel
import com.features.compose_navigation.usecase.GetMemoUseCase
import com.hmju.core.compose.BaseListClickEvent
import com.hmju.core.compose.MutableStateFlowList
import com.hmju.core.ui.observer
import com.hmju.core.ui.stateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 5/6/24
 */
@HiltViewModel
class MemoViewModel @Inject constructor(
    private val getMemoUseCase: GetMemoUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _userId: MutableStateFlow<String> by lazy {
        MutableStateFlow(savedStateHandle["id"] ?: "")
    }
    val userId: StateFlow<String>
        get() = _userId.observer({
            "반갑습니다. $it 님"
        }, "로딩중..", viewModelScope)

    private val _dataList: MutableStateFlowList<MemoUiModel> by lazy { MutableStateFlowList() }
    val dataList: StateFlow<List<MemoUiModel>> get() = _dataList.stateFlow
    private val _clickEvent: MutableStateFlow<MemoClickEvent> by lazy {
        MutableStateFlow(MemoClickEvent.Init)
    }

    @OptIn(FlowPreview::class)
    val clickEvent: StateFlow<MemoClickEvent>
        get() = _clickEvent
            .debounce(200)
            .stateIn(MemoClickEvent.Init, viewModelScope)

    fun start() {
        handleRandomTitle()
        reqMemoList()
    }

    private fun handleRandomTitle() {
        val originId = savedStateHandle.get<String>("user_id") ?: return
        val sdf = SimpleDateFormat("mm분 ss초", Locale.getDefault())

        viewModelScope.launch {
            repeat(300) {
                delay(1000)
                _userId.emit("$originId ${sdf.format(System.currentTimeMillis())}")
            }
        }
    }

    private fun reqMemoList() {
        // _dataList.add(MemoUiModel.Empty)
        getMemoUseCase()
            .onStart { }
            .onEach { _dataList.addAll(it) }
            .catch { Timber.d("ERROR $it") }
            .launchIn(viewModelScope)
    }

    fun setClickEvent(newEvent: BaseListClickEvent) {
        if (newEvent !is MemoClickEvent) return
        _clickEvent.value = newEvent
    }
}
