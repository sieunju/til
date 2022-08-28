package com.features.base_mvvm.ui.mvvm_lifecycle

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm.usecase.GetGoodsUseCase
import com.features.core_ui.base.ActivityViewModel
import com.features.core_ui.base.IntentKey
import com.features.core_ui.lifecycle.OnActivityResult
import com.features.core_ui.lifecycle.OnCreated
import com.features.core_ui.lifecycle.OnResumed
import com.hmju.core.data.model.params.GoodsParamMap
import com.hmju.core.login_manager.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

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

    @OnCreated
    fun onCreate() {
        Timber.d("Token ${savedStateHandle.get<String>(IntentKey.TOKEN)}")
        Timber.d("NowTime ${savedStateHandle.get<Long>(IntentKey.NOW_TIME)}")
        Timber.d("Test Long Arr ${savedStateHandle.get<LongArray>(IntentKey.TEST_LONG_ARR)}")
    }

    @OnResumed
    fun onResume() {
//        activityStack.value = getActivityStackStr()
//        fragmentStack.value = getFragmentStackStr()
    }

    fun changeToken() {
        getGoodsUseCase(GoodsParamMap())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loginManager.setToken(it[Random.nextInt(10)].message)
            }, {

            })
    }

    fun moveTest3Page() {
        startMovePageEvent.value = null
    }

    @OnActivityResult(2000)
    fun on2000Result(data: Bundle?) {
        Timber.d("[s] Result Data 2000 ==================================================")
        data?.let {
            it.keySet().forEach { str ->
                Timber.d("Key $str Value ${it.get(str)}")
            }
        }
        Timber.d("[e] Result Data 2000 ==================================================")
    }
}
