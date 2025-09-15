package com.features.compose_ui

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewModelScope
import com.hmju.core.ui.base.ActivityViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2023/07/23
 */
@HiltViewModel
class ComposeUiActivityViewModel @Inject constructor(

) : ActivityViewModel() {
    sealed interface UiState {
        object Loading : UiState

        data class Success(
            val value: String
        ) : UiState

        data class Error(val err: String) : UiState
    }

    private val _uiState: MutableStateFlow<UiState> by lazy { MutableStateFlow(UiState.Loading) }

    val message: StateFlow<String> = _uiState
        .map { if (it is UiState.Success) it.value else "" }
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialValue = "33")

    override fun onDirectCreate() {
        super.onDirectCreate()
    }
}
