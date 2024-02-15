package com.features.base_mvvm.ui.mvvm_lifecycle

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm.usecase.GetGoodsUseCase
import com.hmju.core.login_manager.LoginManager
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.ActivityResult
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core.ui.base.IntentKey
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val queryMap: PagingQueryParams by lazy { PagingQueryParams() }

    override fun onDirectViewCreated() {
        super.onDirectViewCreated()
        _activityResult.postValue("qwerqwer")
        _activityResult.value = "asdfasdf"
    }

    override fun onDirectResumed() {
        super.onDirectResumed()
        //        activityStack.value = getActivityStackStr()
//        fragmentStack.value = getFragmentStackStr()
    }

    fun move200Page() {
        ActivityResult.Builder(MvvmLifecycleTest2Activity::class)
            .setRequestCode(200)
            .setBundle(IntentKey.TOKEN, loginManager.getToken())
            .movePage()
    }

    fun move201Page() {
        ActivityResult.Builder(MvvmLifecycleTest2Activity::class)
            .setRequestCode(201)
            .setBundle(IntentKey.NOW_TIME, System.currentTimeMillis())
            .movePage()
    }

    override fun onActivityResult(reqCode: Int, resCode: Int, data: Bundle) {
        super.onActivityResult(reqCode, resCode, data)
        when (reqCode) {
            200 -> {
                _activityResult.value =
                    "${200}_${data.getString(IntentKey.TOKEN)}, ${data.getLong(IntentKey.NOW_TIME)}"
            }

            201 -> {
                _activityResult.value =
                    "${201}_${data.getString(IntentKey.TOKEN)}, ${data.getLong(IntentKey.NOW_TIME)}"
            }
        }
    }
}
