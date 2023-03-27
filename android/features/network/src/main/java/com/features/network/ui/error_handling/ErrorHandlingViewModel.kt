package com.features.network.ui.error_handling

import com.features.network.ApiService
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
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
//        jSendRepository.fetchJSend().subscribe().addTo(compositeDisposable)
        apiService.fetchJSend().subscribe({
            Timber.d("다 왔습니다. $it")
        }, {

        }).addTo(compositeDisposable)
        apiService.fetchJSendListWithMeta().subscribe({
            Timber.d("여깁니다 $it")
            it.payload
        }, {}).addTo(compositeDisposable)
        apiService.fetchJSendListWithMeta()
            .map {
                Timber.d("Map Thread ${Thread.currentThread()}")
                return@map it
            }
            .subscribe({
                Timber.d("Thread ${Thread.currentThread()}")
                Timber.d("SUCC ${it}")
            }, {

            }).addTo(compositeDisposable)
//        errorHandlingRepository.getError505()
//            .subscribe({
//            },{
//
//            })
//            .addTo(compositeDisposable)
    }

    fun performPost505() {
        apiService.postError505()
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun performGet404() {
        apiService.getError404()
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun performPost404() {
        apiService.postError404()
            .subscribe()
            .addTo(compositeDisposable)
    }
}
