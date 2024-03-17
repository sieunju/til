package com.features.base_mvvm_lifecycle.ui.test_3

import androidx.lifecycle.viewModelScope
import com.features.base_mvvm_lifecycle.ui.test_2.MvvmLifecycleTest2Activity
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
 * Created by juhongmin on 2022/03/12
 */
@HiltViewModel
class MvvmLifecycleTest3ViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase
) : ActivityViewModel() {

    override fun onDirectCreate() {
        super.onDirectCreate()
        savedStateHandle.keys().forEach {
            Timber.d("Key $it ${getIntentData<Any>(it)}")
        }
        setResultSaveData("TEST_KEY", "TETKKEKEKQKEKQKEKEKEKEKEKE")
        start()
    }

    override fun onDirectStop() {
        super.onDirectStop()
        setResultSaveData("TEST_KEY", "AAEFEFEFEFEFEFEFEFEFA")
    }


    private fun start() {
        val queryMap = PagingQueryParams()
        viewModelScope.launch {
            Timber.d("SUCC ${getGoodsUseCase(queryMap)}")
        }
    }

    fun moveTest2Page() {
        ActivityResult.Builder(MvvmLifecycleTest2Activity::class)
            .setBundle(IntentKey.NOW_TIME, System.currentTimeMillis())
            .movePage()
    }
}