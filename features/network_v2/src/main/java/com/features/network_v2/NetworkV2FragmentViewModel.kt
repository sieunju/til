package com.features.network_v2

import androidx.lifecycle.viewModelScope
import com.features.network_v2.model.TokenBody
import com.hmju.core.model.base.onError
import com.hmju.core.model.base.onSuccess
import com.hmju.core.model.error.JSendException
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
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

    private val json: Json by lazy {
        Json {
            isLenient = true // Json 큰따옴표 느슨하게 체크.
            ignoreUnknownKeys = true // Field 값이 없는 경우 무시
            coerceInputValues = true // "null" 이 들어간경우 default Argument 값으로 대체
        }
    }

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

    fun onRefreshToken() {
        viewModelScope.launch {
            apiService.postToken(TokenBody("hohoh.com"))
                .onSuccess {
                    Timber.d("SUCC ${it.payload}")
                    Timber.d("Decode Token ${it.payload.getPayload(json)}")
                }
        }
    }
}
