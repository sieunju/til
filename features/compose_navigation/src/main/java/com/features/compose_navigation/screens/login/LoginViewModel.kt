package com.features.compose_navigation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmju.core.ui.stateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Description : 로그인 화면 관련 ViewModel
 *
 * Created by juhongmin on 4/7/24
 */
@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {

    val id: MutableStateFlow<String> by lazy { MutableStateFlow("") }
    val password: MutableStateFlow<String> by lazy { MutableStateFlow("") }
    val loginEnable: StateFlow<Boolean> = id.combine(password) { id, pw ->
        isValidateId(id) && isValidatePw(pw)
    }.stateIn(false, viewModelScope)


    fun start() {

    }

    private fun isValidateId(id: String): Boolean {
        return id.length > 4
    }

    private fun isValidatePw(pw: String): Boolean {
        return pw.length > 8
    }
}
