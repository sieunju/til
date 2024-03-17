package com.features.base_mvvm_lifecycle.ui.test_3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/03/17
 */
@HiltViewModel
class MvvmLifecycleTest3FragmentViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle
) : FragmentViewModel() {

    val startFragment: MutableLiveData<Unit> by lazy { MutableLiveData() }
    val startToast: MutableLiveData<String> by lazy { MutableLiveData() }

    override fun onDirectCreatedToResumed() {
        super.onDirectCreatedToResumed()
        startToast.value = "테스트 3 완료"
        stateHandle["TEST_KEY"] = "adfasdfasdf"
    }

    fun moveTest4Fragment() {
        startFragment.value = Unit
    }
}