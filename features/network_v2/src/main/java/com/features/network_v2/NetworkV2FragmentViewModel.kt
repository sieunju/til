package com.features.network_v2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.features.network_v2.models.body.ErrorBody
import com.features.network_v2.models.entity.JSendEntity
import com.hmju.core.models.base.onError
import com.hmju.core.models.base.onSuccess
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
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
internal class NetworkV2FragmentViewModel @Inject constructor(
    private val apiService: ApiService
) : FragmentViewModel() {

    private val _progressText: MutableLiveData<String> by lazy { MutableLiveData() }
    val progressText: LiveData<String> get() = _progressText
    private val _isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun onRequestError() {
        viewModelScope.launch {
            apiService.fetchError404()
                .onSuccess { Timber.d("SUCC $it") }
                .onError {
                    Timber.d("ERROR $it")
                    Timber.d("ERROR ${it.err.getBody<ErrorBody>()}")
                }
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
                Timber.d("RESULT $it 걸린 시간 ${System.currentTimeMillis() - startTime}")
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
                    .map { it.obj }
                    .onErrorReturn { JSendEntity() }
                )
            } else if (ranInt < 6) {
                works.add(apiService.fetchJSendRx())
            } else {
                PagingQueryParams(
                    pageNo = Random.nextInt(5).plus(1),
                    pageSize = 100
                )
                val params = PagingQueryParams(
                    pageNo = Random.nextInt(5).plus(1),
                    pageSize = 100
                )
                works.add(apiService.fetchGoodsRx(params.getQueryMap()))
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
            .doOnNext {
                startJwtRequestCo(it)
                // startJwtRequest(it).subscribe().addTo(compositeDisposable)
            }
            // .flatMap { startJwtRequest(it).toFlowable() }
            .doOnNext {
                val percentage = (it.toFloat() / takeCount) * 100.0
                _progressText.postValue("${percentage.toInt()}%")
            }
            .doFinally {
                _progressText.postValue("완료")
                _isLoading.postValue(false)
            }
            .doOnCancel {
                _progressText.postValue("취소")
                _isLoading.postValue(false)
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

    private fun startJwtRequestCo(percent: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val startNs = System.nanoTime()
            val work1 = async { apiService.fetchJwtTestCo(300) }
            val work2 = async { apiService.fetchJwtTest1Co(1000) }
            val work3 = async { apiService.fetchJwtTest2Co(200) }
            awaitAll(work1, work2, work3)
            val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
            Timber.d("작업 완료 ${tookMs}ms ${Thread.currentThread()}")
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
        val queryMap = PagingQueryParams()
        queryMap.pageNo = 3
        queryMap.pageSize = 30
        return apiService.fetchJSendRx()
            .flatMap { apiService.fetchGoodsRx(queryMap.getQueryMap()) }
            .map { 1 }
            .onErrorReturn { 1 }
            .subscribeOn(Schedulers.io())
    }

    private fun reqTest2(): Single<Int> {
        val queryMap = PagingQueryParams()
        queryMap.pageNo = 3
        queryMap.pageSize = 30
        return Single.zip(
            apiService.fetchGoodsRx(queryMap.getQueryMap())
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
        val queryMap = PagingQueryParams()
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
        return apiService.fetchJwtTest(300)
            .map { 1 }
            .onErrorReturn { 11 }
            .subscribeOn(Schedulers.io())
    }

    private fun reqJwtTest2(): Single<Int> {
        return apiService.fetchJwtTest1(1000)
            .map { 2 }
            .onErrorReturn { 12 }
            .subscribeOn(Schedulers.io())
    }

    private fun reqJwtTest3(): Single<Int> {
        return apiService.fetchJwtTest2(200)
            .map { 3 }
            .onErrorReturn { 13 }
            .subscribeOn(Schedulers.io())
    }
}
