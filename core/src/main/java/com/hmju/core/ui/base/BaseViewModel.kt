package com.hmju.core.ui.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
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

    /**
     * StateFlow Observer 처리하는 함수
     * [SharingStarted.WhileSubscribed] 정책
     * stopTimeoutMillis Collector 가 모두 사라진 이후 정치할 Delay
     * replayExpirationMillis 캐싱할 값을 유지할 시간
     * ex.)
     * private val _uiStateFlow = MutableStateFlow<UiState>()
     * val successMessage: StateFlow<String> = _uiStateFlow
     *         .observer({ if (it is UiState.Success) it.value else "none" }, "default")
     *
     * @param transform Map 기능을 하는 함수 [Transformations.map] 동일
     * @param initValue NotNull 초기 값
     */
    protected inline fun <reified T, I : Any> Flow<I>.observer(
        crossinline transform: suspend (value: I) -> T,
        initValue: T
    ): StateFlow<T> {
        return transform { value ->
            return@transform emit(transform(value))
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(
                stopTimeoutMillis = 0,
                replayExpirationMillis = 3000
            ),
            initValue
        )
    }


    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
