package com.features.base_mvvm

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm.ui.mvvm_lifecycle.MvvmLifecycleTestActivity
import com.hmju.core.model.test.SerializableEntity
import com.hmju.core.ui.base.ActivityResult
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
class RefactorTestViewModel @Inject constructor(
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

    fun onResult() {
        ActivityResult.Builder(MvvmLifecycleTestActivity::class)
            .setRequestCode(300)
            .setBundle("Serializable", SerializableEntity("testTitle", System.currentTimeMillis()))
            .movePage()
    }

    override fun onActivityResult(reqCode: Int, resCode: Int, data: Bundle) {
        super.onActivityResult(reqCode, resCode, data)
        when (reqCode) {
            3000 -> {
                if (resCode == Activity.RESULT_CANCELED) {
                    Timber.d("[s] Cancel Result Data ==================================================")
                    val builder = StringBuilder()
                    data.keySet().forEach { key ->
                        Timber.d("Key $key Value ${data.get(key)}")
                        builder.append("Key $key Value ${data.get(key)}\n")
                    }
                    _contents.value = builder.toString()
                    Timber.d("[e] Cancel Result Data ==================================================")
                } else {
                    Timber.d("[s] Result Data ==================================================")
                    val builder = StringBuilder()
                    data.keySet().forEach { key ->
                        Timber.d("Key $key Value ${data.get(key)}")
                        builder.append("Key $key Value ${data.get(key)}\n")
                    }
                    _contents.value = builder.toString()
                    Timber.d("[e] Result Data ==================================================")
                }
            }

        }
    }
}
