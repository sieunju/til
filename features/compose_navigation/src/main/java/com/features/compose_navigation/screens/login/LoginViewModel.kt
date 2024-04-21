package com.features.compose_navigation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    @OptIn(FlowPreview::class)
    fun start() {
        Timber.d("Start Thread ${Thread.currentThread()}")
        viewModelScope.launch {
            id.debounce(200L)
                .collectLatest { Timber.d("ID $it") }
        }
        viewModelScope.launch {
            password
                .debounce(200L)
                .collectLatest { Timber.d("PW $it") }
        }
    }
}
