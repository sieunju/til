package com.features.network_v2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.features.network_v2.model.JSendEntity
import com.hmju.core.model.base.onError
import com.hmju.core.model.base.onSuccess
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.pref.PreferenceManager
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import timber.log.Timber
import java.util.concurrent.TimeUnit
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
    private val prefManager: PreferenceManager
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

    private val _progressText: MutableLiveData<String> by lazy { MutableLiveData() }
    val progressText: LiveData<String> get() = _progressText
    private val _isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun onRequestError() {
        viewModelScope.launch {
            apiService.fetchError404()
                .onSuccess { Timber.d("SUCC $it") }
                .onError { Timber.d("ERROR ${it.err.getBody<ErrorBody>()}") }
        }
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

    private var disposable: Disposable? = null

    fun handleRefreshTokenTest(minute: Int) {
        disposable?.dispose()
        disposable = null
        _isLoading.value = true
        val takeMs = minute * (1000 * 60)
        val endTimeMs = System.currentTimeMillis().plus(takeMs)
        val takeCount = takeMs.toFloat() / 333.0
        disposable = Flowable.interval(0L, 333L, TimeUnit.MILLISECONDS)
            .takeUntil { System.currentTimeMillis() >= endTimeMs }
            .doOnNext { startRequest() }
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterNext {
                val percentage = (it.toFloat() / takeCount) * 100.0
                _progressText.value = "${percentage.toInt()}%"
            }
            .doFinally {
                _progressText.value = "완료"
                _isLoading.value = false
            }
            .doOnCancel {
                _progressText.value = "완료"
                _isLoading.value = false
            }
            .subscribe()
    }

    private fun startRequest() {
        Single.mergeDelayError(
            reqTest1(),
            reqTest2(),
            reqTest3()
        ).subscribe().addTo(compositeDisposable)
    }

    private fun reqTest1(): Single<Int> {
        val queryMap = GoodsParameter()
        queryMap.pageNo = 3
        queryMap.pageSize = 30
        return apiService.fetchJSendRx()
            .flatMap { apiService.fetchGoodsRx(queryMap.getQueryParameter()) }
            .map { 1 }
            .onErrorReturn { 1 }
    }

    private fun reqTest2(): Single<Int> {
        val queryMap = GoodsParameter()
        queryMap.pageNo = 3
        queryMap.pageSize = 30
        return Single.zip(
            apiService.fetchGoodsRx(queryMap.getQueryParameter())
                .subscribeOn(Schedulers.io()),
            apiService.fetchJSendRx()
                .subscribeOn(Schedulers.io()),
            apiService.fetchTest()
                .subscribeOn(Schedulers.io())
        ) { _, _, _ ->
            return@zip 2
        }.onErrorReturn { 2 }.subscribeOn(Schedulers.io())
    }

    private fun reqTest3(): Single<Int> {
        val queryMap = GoodsParameter()
        queryMap.pageNo = 3
        queryMap.pageSize = 30
        return Single.zip(
            apiService.fetchAndroid(),
            apiService.fetchAndroid(),
            apiService.fetchTest()
        ) { _, _, _ -> }
            .map { 3 }
            .onErrorReturn { 3 }
            .subscribeOn(Schedulers.io())
    }
}
