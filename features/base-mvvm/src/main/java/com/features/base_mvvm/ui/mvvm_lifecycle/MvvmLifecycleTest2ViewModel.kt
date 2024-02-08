package com.features.base_mvvm.ui.mvvm_lifecycle

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm.usecase.GetGoodsUseCase
import com.hmju.core.login_manager.LoginManager
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.IntentKey
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/03/11
 */
@HiltViewModel
class MvvmLifecycleTest2ViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase,
    private val loginManager: LoginManager
) : ActivityViewModel() {

    val startMovePageEvent: MutableLiveData<Unit> by lazy { MutableLiveData() }

    override fun onDirectCreate() {
        super.onDirectCreate()
        Timber.d("Token ${savedStateHandle.get<String>(IntentKey.TOKEN)}")
        Timber.d("NowTime ${savedStateHandle.get<Long>(IntentKey.NOW_TIME)}")
        Timber.d("Test Long Arr ${savedStateHandle.get<LongArray>(IntentKey.TEST_LONG_ARR)}")
    }

    override fun onDirectResumed() {
        super.onDirectResumed()
        // activityStack.value = getActivityStackStr()
        // fragmentStack.value = getFragmentStackStr()
    }

    fun changeToken() {
        getGoodsUseCase(GoodsParameter())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {

            }).addTo(compositeDisposable)
    }

    fun moveTest3Page() {
        startMovePageEvent.value = null
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
