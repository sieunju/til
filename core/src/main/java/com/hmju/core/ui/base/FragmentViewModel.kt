package com.hmju.core.ui.base

import android.os.Looper
import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description : Fragment 전용 ViewModel 클래스
 * Fragment 사용되는 공통 LiveData 들을 정의합니다.
 * Created by juhongmin on 2022/04/15
 */
@Suppress("unused")
@HiltViewModel
open class FragmentViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var savedStateHandle: SavedStateHandle

    private val _startActivityPage: MutableLiveData<ActivityResult> by lazy { MutableLiveData() }
    val startActivityPage: LiveData<ActivityResult> get() = _startActivityPage

    /**
     * Fragment onHiddenChanged (hidden == false)
     */
    @CallSuper
    open fun onDirectShown(){}

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
