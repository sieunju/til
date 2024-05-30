package com.features.compose_navigation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmju.core.ui.stateIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Description : 회원 가입 ViewModel
 *
 * Created by juhongmin on 4/22/24
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {

    val id: MutableStateFlow<String> by lazy { MutableStateFlow("qewrt") }
    val password: MutableStateFlow<String> by lazy { MutableStateFlow("123456789") }
    val passwordConfirm: MutableStateFlow<String> by lazy { MutableStateFlow("123456789") }
    val isSignUpEnable: StateFlow<Boolean>
        get() = combine(id, password, passwordConfirm) { id, pw, pwConfig ->
            isValidateId(id) && isValidatePw(pw) && pw == pwConfig
        }.stateIn(false, viewModelScope)

    private fun isValidateId(id: String): Boolean {
        return id.length > 4
    }

    private fun isValidatePw(pw: String): Boolean {
        return pw.length > 8
    }
}
