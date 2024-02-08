package com.hmju.core.ui.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

/**
 * Description : 좀더 Activity or Lifecycle 에 대해서 좀더 간단히 처리하기 위한
 * ViewModel2 클래스
 *
 * Created by juhongmin on 2022/04/13
 */
@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected var lifecycleEvent: Lifecycle.Event = Lifecycle.Event.ON_ANY

    fun getCurrentLifecycle() = lifecycleEvent

    /**
     * 현재 Lifecycle 업데이트 하는 함수
     * @param newLifecycle Lifecycle Event
     */
    fun setLifecycle(newLifecycle: Lifecycle.Event) {
        lifecycleEvent = newLifecycle
    }

//    /**
//     * [OnCreated], [OnResumed], [OnStopped], [OnViewCreated]
//     *
//     * 선언된 함수를 실행 하는 함수
//     */
//    @Deprecated("테스트 결과 별로 좋지 않는 방향이라 Deprecated 합니다.")
//    inline fun <reified T : Annotation> performLifecycle(): Disposable {
//        return Flowable.fromIterable(javaClass.methods.toList())
//            .filter { it.isAnnotationPresent(T::class.java) }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                it.invoke(this)
//            }, {})
//    }

//    /**
//     * onActivityResult 에 대한 처리
//     * ReactiveX 타입
//     * @param reqCode RequestCode
//     * @param resCode ResultCode
//     * @param data 전달 받을 데이터
//     */
//    fun handleActivityResult(reqCode: Int, resCode: Int, data: Bundle?): Disposable {
//        return Flowable.fromIterable(javaClass.methods.toList())
//            .filter { it.isAnnotationPresent(OnActivityResult::class.java) }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnNext { method ->
//                method.getAnnotation(OnActivityResult::class.java)?.let { annotation ->
//                    // RequestCode, RESULT Code 와 같은 함수만 호출
//                    if (annotation.requestCode == reqCode && annotation.resCode == resCode) {
//                        method.invoke(this, data)
//                    }
//                }
//            }.subscribe()
//    }

    /**
     * CompositeDisposable 를 public 으로 처리할수 있는 함수
     * @param disposable Disposable
     */
    fun addDisposable(disposable: Disposable) {
        disposable.addTo(compositeDisposable)
    }

    /**
     * Activity, Fragment Lifecycle Annotation 로 처리하는게 아닌
     * 직접적으로 처리해야 하는 경우 해당 함수 호출합니다.
     * onCreate 에서 바로 onResume 타는 경우
     * ex.) View 단에서 TabLayout 를 셋팅 해야 하는경우
     */
    open fun onDirectCreatedToResumed() {}

    /**
     * Activity, Fragment Lifecycle Annotation 로 처리하는게 아닌
     * 직접적으로 처리해야 하는 경우 해당 함수 호출합니다.
     * ex.) View 단에서 TabLayout 를 셋팅 해야 하는경우
     */
    open fun onDirectResumed() {}

    /**
     * Activity, Fragment Lifecycle Annotation 로 처리하는게 아닌
     * 직접적으로 처리해야 하는 경우 해당 함수 호출합니다.
     * ex.) View 단에서 TabLayout 를 셋팅 해야 하는경우
     */
    open fun onDirectStop() {}

    /**
     * ActivityResult Callback
     * @param reqCode Request Code
     * @param resCode Result Code
     * @param data BundleData
     */
    @CallSuper
    open fun onActivityResult(
        reqCode: Int,
        resCode: Int,
        data: Bundle
    ) {
        Timber.d("ActivityResult ResultCode $resCode ReqCode $reqCode $data")
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
