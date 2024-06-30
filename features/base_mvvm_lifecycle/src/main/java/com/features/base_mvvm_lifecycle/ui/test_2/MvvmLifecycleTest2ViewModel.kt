package com.features.base_mvvm_lifecycle.ui.test_2

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.features.base_mvvm_lifecycle.ui.test_3.MvvmLifecycleTest3Activity
import com.features.base_mvvm_lifecycle.usecase.GetGoodsUseCase
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
 * Created by juhongmin on 2022/03/11
 */
@HiltViewModel
class MvvmLifecycleTest2ViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : ActivityViewModel() {


    override fun onDirectCreate() {
        super.onDirectCreate()
        Timber.d("Token ${savedStateHandle.get<String>(IntentKey.TOKEN)}")
        Timber.d("NowTime ${savedStateHandle.get<Long>(IntentKey.NOW_TIME)}")
        Timber.d("Test Long Arr ${savedStateHandle.get<LongArray>(IntentKey.TEST_LONG_ARR)}")
    }

    fun changeToken() {
        viewModelScope.launch {
            getGoodsUseCase(PagingQueryParams())
        }
    }

    fun moveTest3Page() {
        ActivityResult.Builder(MvvmLifecycleTest3Activity::class)
            .movePage()
    }

    override fun onActivityResult(reqCode: Int, resCode: Int, data: Bundle) {
        super.onActivityResult(reqCode, resCode, data)
        if (reqCode == 2000) {
            Timber.d("[s] Result Data 2000 ==================================================")
            data.keySet().forEach { str ->
                Timber.d("Key $str Value ${data.get(str)}")
            }
            Timber.d("[e] Result Data 2000 ==================================================")
        }
    }
}
