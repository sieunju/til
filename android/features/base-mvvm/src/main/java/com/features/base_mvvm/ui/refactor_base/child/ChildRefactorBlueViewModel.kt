package com.features.base_mvvm.ui.refactor_base.child

import androidx.lifecycle.MutableLiveData
import com.features.core_ui.base.FragmentViewModel
import com.features.core_ui.lifecycle.OnCreated
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/04/18
 */
@HiltViewModel
class ChildRefactorBlueViewModel @Inject constructor(
) : FragmentViewModel() {
    val startBottomSheetDialog: MutableLiveData<Unit> by lazy { MutableLiveData() }
    val startParentBottomSheetDialog: MutableLiveData<Unit> by lazy { MutableLiveData() }

    fun onBottomSheetDialog() {
        startBottomSheetDialog.value = null
    }

    fun onParentBottomSheetDialog() {
        startParentBottomSheetDialog.value = null
    }

    @OnCreated
    fun onCreated() {
        savedStateHandle.keys().forEach {
            Timber.d("Key $it Value ${savedStateHandle.get<Any>(it)}")
        }
    }
}
