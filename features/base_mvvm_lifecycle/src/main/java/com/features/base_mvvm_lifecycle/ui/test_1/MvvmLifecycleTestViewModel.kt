package com.features.base_mvvm_lifecycle.ui.test_1

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.features.base_mvvm_lifecycle.usecase.GetGoodsUseCase
import com.hmju.core.login_manager.LoginManager
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.ActivityResult
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.IntentKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/27
 */
@HiltViewModel
class MvvmLifecycleTestViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : ActivityViewModel() {

    private val _contents: MutableLiveData<String> by lazy { MutableLiveData() }
    val contents: LiveData<String> get() = _contents

    override fun onDirectCreate() {
        super.onDirectCreate()
        val queryMap = PagingQueryParams()
        viewModelScope.launch {
            val list = getGoodsUseCase(queryMap)
            Timber.d("List $list")
        }
    }

    override fun onIntent() {
        super.onIntent()
        Timber.d("[s] onCreate Intent Data ===============================================")
        val builder = StringBuilder()
        savedStateHandle.keys().forEach {
            Timber.d("Key $it Value ${getIntentData<Any>(it)}")
            builder.append("Key $it Value ${getIntentData<Any>(it)}\n")
        }
        _contents.value = builder.toString()
        Timber.d("[s] onCreate Intent Data ===============================================")
    }

    fun onRandomToken() {
        setResultSaveData("HiKey", "dddfefefeffe")
        setResultSaveData("Hello....", System.currentTimeMillis())
        setResultSaveData(IntentKey.TOKEN, "ChangeResult")
        onPageFinish()
    }

    fun moveToTestPage() {
        ActivityResult.Builder(MvvmLifecycleTestActivity::class)
            .setRequestCode(3001)
            .movePage()
    }

    override fun onActivityResult(reqCode: Int, resCode: Int, data: Bundle) {
        super.onActivityResult(reqCode, resCode, data)
        Timber.d("111111111111111111 ${reqCode}")
        when (reqCode) {
            3001 -> {
                if (resCode == Activity.RESULT_OK) {
                    Timber.d("[s] Result Data ==================================================")
                    data.keySet().forEach { str ->
                        Timber.d("Key $str Value ${data.get(str)}")
                    }
                    Timber.d("[e] Result Data ==================================================")
                }
            }
        }
    }
}
