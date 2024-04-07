package com.features.compose_navigation.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Description : 로그인 화면 관련 ViewModel
 *
 * Created by juhongmin on 4/7/24
 */
@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel() {
    val id : MutableStateFlow<String> by lazy { MutableStateFlow("") }
    val password: MutableStateFlow<String> by lazy { MutableStateFlow("") }

    fun start(){

    }
}
