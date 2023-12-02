package com.features.network_v2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.features.network_v2.model.JSendEntity
import com.hmju.core.Constants
import com.hmju.core.model.base.onError
import com.hmju.core.model.base.onSuccess
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.pref.PreferenceManager
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
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

    fun onSimpleTest() {
        val startTime = System.currentTimeMillis()
        Single.zip(
            reqJwtTest1(),
            reqJwtTest2(),
            reqJwtTest3(),
        ) { a, b, c ->
            a.plus(b).plus(c)
        }
            .doOnSuccess {
                Timber.tag("HTTP_LOG")
                    .d("RESULT $it 걸린 시간 ${System.currentTimeMillis() - startTime}")
            }
            .subscribe()
            .addTo(compositeDisposable)
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

    fun handleRefreshTokenTest(minute: Int) {
        _isLoading.value = true
        val takeMs = minute * (1000 * 60)
        val endTimeMs = System.currentTimeMillis().plus(takeMs)
        val takeCount = takeMs.toFloat() / 333.0
        Flowable.interval(0, 333L, TimeUnit.MILLISECONDS)
            .takeUntil { System.currentTimeMillis() >= endTimeMs }
            .flatMap { startJwtRequest(it).toFlowable() }
            .doOnNext {
                val percentage = (it.toFloat() / takeCount) * 100.0
                _progressText.postValue("${percentage.toInt()}%")
            }
            .doFinally {
                _progressText.postValue("완료 ${Constants.tokenErrorCount}")
                _isLoading.postValue(false)
                Constants.tokenErrorCount = 0
            }
            .doOnCancel {
                _progressText.postValue("취소 ${Constants.tokenErrorCount}")
                _isLoading.postValue(false)
                Constants.tokenErrorCount = 0
            }
            .subscribe().addTo(compositeDisposable)
    }

    private fun startJwtRequest(percent: Long): Single<Long> {
        val startNs = System.nanoTime()
        return Single.zip(
            reqJwtTest1(),
            reqJwtTest2(),
            reqJwtTest3()
        ) { a, b, c ->
            percent
        }.doOnSuccess {
            val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
            Timber.d("작업 완료 ${tookMs}ms")
        }
    }

    fun startRequest() {
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
            .subscribeOn(Schedulers.io())
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
            apiService.fetchJwtTest()
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
            apiService.fetchJwtTest()
        ) { _, _, _ -> }
            .map { 3 }
            .onErrorReturn { 3 }
            .subscribeOn(Schedulers.io())
    }

    private fun reqJwtTest1(): Single<Int> {
        return apiService.fetchJwtTest()
            .map { 1 }
            .onErrorReturn { 11 }
            .subscribeOn(Schedulers.io())
    }

    private fun reqJwtTest2(): Single<Int> {
        return apiService.fetchJwtTest1()
            .map { 2 }
            .onErrorReturn { 12 }
            .subscribeOn(Schedulers.io())
    }

    private fun reqJwtTest3(): Single<Int> {
        return apiService.fetchJwtTest2()
            .map { 3 }
            .onErrorReturn { 13 }
            .subscribeOn(Schedulers.io())
    }
}
