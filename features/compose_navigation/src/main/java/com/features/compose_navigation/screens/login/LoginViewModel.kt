package com.features.compose_navigation.screens.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmju.core.ui.stateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Description : 로그인 화면 관련 ViewModel
 *
 * Created by juhongmin on 4/7/24
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val id: MutableStateFlow<String> by lazy { MutableStateFlow("") }
    val password: MutableStateFlow<String> by lazy { MutableStateFlow("") }
    val loginEnable: StateFlow<Boolean>
        get() = id.combine(password) { id, pw ->
            isValidateId(id) && isValidatePw(pw)
        }.stateIn(false, viewModelScope)

    fun start() {
        viewModelScope.launch {
            savedStateHandle.get<String>("user_id")?.let { id.emit(it) }
            savedStateHandle.get<String>("user_pw")?.let { password.emit(it) }
        }
    }

    private fun isValidateId(id: String): Boolean {
        return id.length > 4
    }

    private fun isValidatePw(pw: String): Boolean {
        return pw.length > 8
    }
}
