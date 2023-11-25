package com.features.network_v2

import androidx.lifecycle.viewModelScope
import com.features.network_v2.model.JSendEntity
import com.features.network_v2.model.TokenBody
import com.hmju.core.model.base.onError
import com.hmju.core.model.base.onSuccess
import com.hmju.core.model.error.JSendException
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.pref.PreferenceManager
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import timber.log.Timber
import java.text.SimpleDateFormat
import javax.inject.Inject
import kotlin.random.Random

/**
 * Description :
 *
 * Created by juhongmin on 11/22/23
 */
@HiltViewModel
class NetworkV2FragmentViewModel @Inject constructor(
    private val apiService: ApiService,
    private val prefManager: PreferenceManager,
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

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        prefManager.setValue(PreferenceManager.KEY_TOKEN_EXPIRED_MS, System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일 kk시 mm분 ss초")
        Timber.d("저장된 만료 날짜 ${dateFormat.format(prefManager.getLong(PreferenceManager.KEY_TOKEN_EXPIRED_MS))}")
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
//        viewModelScope.launch {
//            apiService.postToken(TokenBody("hohoh.com"))
//                .onSuccess {
//                    prefManager.setValue(
//                        PreferenceManager.KEY_TOKEN_EXPIRED_MS,
//                        it.payload.getPayload(json).getExpiredMs()
//                    )
//                }
//        }

        for (idx in 0 until 30) {
            requestMultipleApi()
        }
    }

    fun reqTest() {
        requestMultipleApi()
//        viewModelScope.launch {
//            for (idx in 0 until 100) {
//                for (idx in 0 until 30) {
//                    requestMultipleApi()
//                }
//                delay(10_000)
//            }
//            Timber.d("끝났습니다.....")
//        }

    }

    fun requestMultipleApi() {
        val works = mutableListOf<Single<*>>()
        for (idx in 0 until 30) {
            val ranInt = Random.nextInt(10)
            if (ranInt < 3) {
                works.add(apiService.fetchError404Rx()
                    .map { it.payload }
                    .onErrorReturn { JSendEntity() }
                )
            } else if (ranInt < 6) {
                works.add(apiService.fetchJSendRx())
            } else {
                works.add(
                    apiService.fetchGoodsRx(
                        GoodsParameter(
                            pageNo = Random.nextInt(5).plus(1),
                            pageSize = 100
                        ).getQueryParameter()
                    )
                )
            }
        }

        Single.mergeDelayError(works)
            .buffer(works.size)
            .doOnNext { Timber.d("${it.size}") }
            // .doFinally { Timber.d("작업 완료") }
            .subscribe()
            .addTo(compositeDisposable)
    }
}
