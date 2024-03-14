package com.features.base_mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm_lifecycle_bridge.BaseMvvmLifecycleBridge
import com.features.base_mvvm_lifecycle_bridge.SerializableEntity
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.IntentKey
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/03/19
 */
@HiltViewModel
class RefactorBaseTestViewModel @Inject constructor(
    private val mvvmLifecycleBridge: BaseMvvmLifecycleBridge
) : ActivityViewModel() {

    private val _title: MutableLiveData<String> by lazy { MutableLiveData() }
    val title: LiveData<String> get() = _title
    private val _contents: MutableLiveData<String> by lazy { MutableLiveData() }
    val contents: LiveData<String> get() = _contents

    override fun onIntent() {
        super.onIntent()
        Timber.d("[s] onCreate Intent Data ===============================================")
        savedStateHandle.keys().forEach {
            Timber.d("Key $it Value ${savedStateHandle.get<Any>(it)}")
        }
        Timber.d("[s] onCreate Intent Data ===============================================")
    }

    override fun onDirectCreate() {
        super.onDirectCreate()
        _title.value = savedStateHandle.get<String>(IntentKey.TOKEN) ?: run { "Data 가 없습니다.." }
    }

    fun moveToMVVMLifecycleFeature() {
        mvvmLifecycleBridge.moveToPage(SerializableEntity("testTitle", System.currentTimeMillis()))
    }
}
