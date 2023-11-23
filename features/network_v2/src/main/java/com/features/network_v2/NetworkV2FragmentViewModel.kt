package com.features.network_v2

import androidx.lifecycle.viewModelScope
import com.hmju.core.model.base.onError
import com.hmju.core.model.base.onSuccess
import com.hmju.core.model.error.JSendException
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 11/22/23
 */
@HiltViewModel
class NetworkV2FragmentViewModel @Inject constructor(
    private val apiService: ApiService,
) : FragmentViewModel() {

    @Serializable
    data class ErrorBody(
        val status: Boolean = false,
        val message: Message = Message(),
    ) {
        @Serializable
        data class Message(
            val name: String = "",
            val contents: String = "",
        )
    }


    fun onRequestGoods() {
        viewModelScope.launch {
            val params = GoodsParameter()
            apiService
                .fetchGoods(params.getQueryParameter())
                .onSuccess { Timber.d("SUCC $it") }
                .onError { Timber.d("ERROR $it") }
        }
    }

    fun onRequestError() {
        viewModelScope.launch {
            apiService.fetchError404()
                .onSuccess { Timber.d("SUCC $it") }
                .onError {
                    val err = it.err
                    if (err is JSendException) {
                        Timber.d("ERROR ${err.getBody<ErrorBody>()}")
                    }
                }
        }
    }
}
