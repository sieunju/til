package com.hmju.core.ui.base

import android.os.Bundle
import android.os.Looper
import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.bumptech.glide.RequestManager
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
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
    private val _startFinishEvent: MutableLiveData<Unit> by lazy { MutableLiveData() }
    val startFinishEvent: LiveData<Unit> get() = _startFinishEvent

    // onCreateView -> onDestroyView
    private var _requestManager: RequestManager? = null
    val requestManager: RequestManager get() = _requestManager!!

    /**
     * Fragment onHiddenChanged (hidden == false)
     */
    open fun onDirectShown() {}

    /**
     * Fragment Lifecycle Annotation 로 처리하는게 아닌
     * 직접적으로 처리해야 하는 경우 해당 함수 호출합니다.
     * ex.) View 단에서 TabLayout 를 셋팅 해야 하는경우
     */
    open fun onDirectViewCreated() {}

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

    /**
     * SavedStateHandle 사용할수 있는 상태인지 체크하는 함수
     * @return true 사용가능한 상태, false 사용 불가능한 상태
     */
    fun checkBundleEnable(): Boolean {
        return this::savedStateHandle.isInitialized
    }

    /**
     * Glide RequestManager 초기화
     */
    fun initRequestManager(requestManager: RequestManager) {
        _requestManager = requestManager
    }

    /**
     * onDestroyView 상태일때 RequestManager 메모리 해제 하는 함수
     */
    fun clearRequestManager() {
        _requestManager = null
    }

    /**
     * FragmentResult Callback
     * @param reqCode Request Code
     * @param resCode Result Code
     * @param data BundleData
     */
    @CallSuper
    open fun onFragmentResult(
        reqCode: Int,
        resCode: Int,
        data: Bundle
    ) {
        Timber.d("FragmentResult ResultCode $resCode ReqCode $reqCode $data")
    }

    /**
     * Activity Page Finish
     * 공통 처리함수
     */
    fun onPageFinish() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            _startFinishEvent.value = Unit
        } else {
            _startFinishEvent.postValue(Unit)
        }
    }
}
