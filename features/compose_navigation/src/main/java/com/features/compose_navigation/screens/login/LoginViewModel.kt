package com.features.compose_navigation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    fun start() {
        Timber.d("Start Thread ${Thread.currentThread()}")
        viewModelScope.launch {
            id.collectLatest {
                Timber.d("입력한 ID값 $it")
            }
        }
    }
}
