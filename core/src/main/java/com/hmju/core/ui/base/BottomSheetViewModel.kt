package com.hmju.core.ui.base

import android.os.Looper
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description : BottomSheet 전용 ViewModel
 *
 * Created by juhongmin on 2022/04/15
 */
@Suppress("unused")
@HiltViewModel
open class BottomSheetViewModel @Inject constructor() : BaseViewModel() {

    private val _startActivityPage: MutableLiveData<ActivityResult> by lazy { MutableLiveData() }
    val startActivityPage: LiveData<ActivityResult> get() = _startActivityPage

    /**
     * Fragment Lifecycle Annotation 로 처리하는게 아닌
     * 직접적으로 처리해야 하는 경우 해당 함수 호출합니다.
     * ex.) View 단에서 TabLayout 를 셋팅 해야 하는경우
     */
    @CallSuper
    open fun onDirectViewCreated() {
        lifecycleEvent = Lifecycle.Event.ON_CREATE
    }

    /**
     * 페이지 이동 관련 확장 함수
     * @sample
     * ActivityResult.Builder().moveToPage()
     *
     */
    protected fun ActivityResult.Builder.movePage() {
        val page = build()
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                _startActivityPage.value = page
            } else {
                _startActivityPage.postValue(page)
            }
        } catch (ex: Exception) {
            _startActivityPage.postValue(page)
        }
    }
}
