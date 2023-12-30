package com.features.base_mvvm.ui.mvvm_lifecycle

import com.features.base_mvvm.usecase.GetGoodsUseCase
import com.hmju.core.login_manager.LoginManager
import com.hmju.core.model.params.GoodsParameter
import com.hmju.core.ui.base.ActivityResult
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
 * Created by juhongmin on 2022/03/12
 */
@HiltViewModel
class MvvmLifecycleTest3ViewModel @Inject constructor(
    private val getGoodsUseCase: GetGoodsUseCase,
    private val loginManager: LoginManager
) : ActivityViewModel() {

    override fun onDirectCreate() {
        super.onDirectCreate()
        savedStateHandle.keys().forEach {
            Timber.d("Key $it ${getIntentData<Any>(it)}")
        }
        setResultSaveData("TEST_KEY", "TETKKEKEKQKEKQKEKEKEKEKEKE")
        start()
        // activityStack.value = getActivityStackStr()
        // fragmentStack.value = getFragmentStackStr()
    }

    override fun onDirectResumed() {
        super.onDirectResumed()
        // activityStack.value = getActivityStackStr()
        // fragmentStack.value = getFragmentStackStr()
    }

    override fun onDirectStop() {
        super.onDirectStop()
        setResultSaveData("TEST_KEY", "AAEFEFEFEFEFEFEFEFEFA")
    }


    private fun start() {
        val queryMap = GoodsParameter()
        getGoodsUseCase(queryMap)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("SUCC $it")
            }, {
                Timber.e("Error $it")
            }).addTo(compositeDisposable)
    }

    fun moveTest2Page() {
        ActivityResult.Builder(MvvmLifecycleTest2Activity::class)
            .setBundle(IntentKey.NOW_TIME, System.currentTimeMillis())
            .movePage()
    }
}