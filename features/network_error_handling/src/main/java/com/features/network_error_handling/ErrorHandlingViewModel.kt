package com.features.network_error_handling

import androidx.lifecycle.viewModelScope
import com.hmju.core.models.base.onError
import com.hmju.core.models.base.onSuccess
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Description : HTTP 에러 헨들링 ViewModel
 *
 * Created by juhongmin on 2022/05/12
 */
@HiltViewModel
class ErrorHandlingViewModel @Inject constructor(
    private val apiService: ApiService
) : FragmentViewModel() {

    fun performGet505() {
        val work1 = viewModelScope.async {
            apiService.fetchJSend()
                .onSuccess { Timber.d("Success $it") }
        }
        val work2 = viewModelScope.async {
            apiService.fetchJSendListWithMeta()
                .onSuccess { Timber.d("여깁니다 ${it.list}") }
        }
        val work3 = viewModelScope.async(Dispatchers.IO) {
            apiService.fetchJSendWithMeta()
                .onSuccess {
                    Timber.d("Thread ${Thread.currentThread()}")
                    Timber.d("SUCC $it")
                }
        }

        viewModelScope.launch {
            awaitAll(work1, work2, work3)
        }
    }

    fun performPost505() {
        viewModelScope.launch {
            apiService.postError505()
                .onError { Timber.d("Result $it") }
        }
    }

    fun performGet404() {
        viewModelScope.launch {
            apiService.getError404()
                .onError { Timber.d("Result $it") }
        }
    }

    fun performPost404() {
        viewModelScope.launch {
            apiService.postError404()
                .onError { Timber.d("Result $it") }
        }
    }
}
