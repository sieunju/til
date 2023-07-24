package com.hmju.core.ui.base

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
@HiltViewModel
open class FragmentViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var savedStateHandle: SavedStateHandle

    protected val _startActivityPage: MutableLiveData<ActivityResult> by lazy { MutableLiveData() }
    val startActivityPage: LiveData<ActivityResult> get() = _startActivityPage

    /**
     * SavedStateHandle 사용할수 있는 상태인지 체크하는 함수
     * @return true 사용가능한 상태, false 사용 불가능한 상태
     */
    fun checkBundleEnable(): Boolean {
        return this::savedStateHandle.isInitialized
    }
}
