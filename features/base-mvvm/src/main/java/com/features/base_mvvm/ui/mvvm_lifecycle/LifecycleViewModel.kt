package com.features.base_mvvm.ui.mvvm_lifecycle

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm.usecase.GetGoodsUseCase
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.base.IntentKey
import com.hmju.core.ui.lifecycle.OnActivityResult
import com.hmju.core.ui.lifecycle.OnCreated
import com.hmju.core.ui.lifecycle.OnResumed
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.login_manager.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/26
 */
@HiltViewModel
class LifecycleViewModel @Inject constructor(
    private val loginManager: LoginManager,
    private val goodsUseCase: GetGoodsUseCase
) : FragmentViewModel() {

    private val _activityResult: MutableLiveData<String> by lazy { MutableLiveData() }
    val activityResult: LiveData<String> get() = _activityResult
    private val queryMap: GoodsParameter by lazy { GoodsParameter() }

    @OnCreated
    fun onCreate() {
        _activityResult.postValue("qwerqwer")
        _activityResult.value = "asdfasdf"
//        activityStack.value = getActivityStackStr()
//        fragmentStack.value = getFragmentStackStr()
        goodsUseCase(queryMap)
            .subscribe({
                loginManager.setToken(it[0].imagePath)
            }, {
            }).addTo(compositeDisposable)
    }

    @OnResumed
    fun onResume() {
//        activityStack.value = getActivityStackStr()
//        fragmentStack.value = getFragmentStackStr()
    }

    fun move200Page() {
//        movePage(
//            MovePageEvent(
//                MvvmLifecycleTest2Activity::class.java,
//                bundle = Bundle().apply {
//                    putString(IntentKey.TOKEN, loginManager.getToken())
//                },
//                requestCode = 200
//            )
//        )
    }

    fun move201Page() {
//        movePage(
//            MovePageEvent(
//                MvvmLifecycleTest2Activity::class.java,
//                bundle = Bundle().apply {
//                    putLong(IntentKey.NOW_TIME, System.currentTimeMillis())
//                },
//                requestCode = 201
//            )
//        )
    }

    @OnActivityResult(200)
    fun onActivity200(data: Bundle?) {
        _activityResult.value =
            "${200}_${data?.getString(IntentKey.TOKEN)}, ${data?.getLong(IntentKey.NOW_TIME)}"
    }

    @OnActivityResult(201)
    fun onActivity2001(data: Bundle?) {
        _activityResult.value =
            "${201}_${data?.getString(IntentKey.TOKEN)}, ${data?.getLong(IntentKey.NOW_TIME)}"
    }

}
