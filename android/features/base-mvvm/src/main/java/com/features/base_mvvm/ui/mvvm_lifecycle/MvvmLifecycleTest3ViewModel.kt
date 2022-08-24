package com.features.base_mvvm.ui.mvvm_lifecycle

import androidx.lifecycle.SavedStateHandle
import com.features.base_mvvm.usecase.GetGoodsUseCase
import com.features.core_ui.base.ActivityViewModel
import com.features.core_ui.lifecycle.OnCreated
import com.features.core_ui.lifecycle.OnResumed
import com.features.core_ui.lifecycle.OnStopped
import com.hmju.core.data.model.params.GoodsParamMap
import com.hmju.shared.login_manager.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/03/12
 */
@HiltViewModel
class MvvmLifecycleTest3ViewModel @Inject constructor(
    private val handle: SavedStateHandle,
    private val getGoodsUseCase: GetGoodsUseCase,
    private val loginManager: LoginManager
) : ActivityViewModel() {

    @OnCreated
    fun savedHandle() {
        handle.keys().forEach {
            Timber.d("Key $it ${handle.get<Any>(it)}")
        }
        handle["TEST_KEY"] = "TETKKEKEKQKEKQKEKEKEKEKEKE"
//        activityStack.value = getActivityStackStr()
//        fragmentStack.value = getFragmentStackStr()
    }

    @OnResumed
    fun onResume() {
//        activityStack.value = getActivityStackStr()
//        fragmentStack.value = getFragmentStackStr()
    }

    @OnStopped
    fun onStop() {
//        saveResultData(Bundle().apply {
//            putString("TEST_KEY", "AAEFEFEFEFEFEFEFEFEFA")
//        })
    }

    @OnCreated
    fun start() {
        val queryMap = GoodsParamMap()
        getGoodsUseCase(queryMap)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                loginManager.setToken(it[0].imagePath)
                Timber.d("SUCC $it")
            }, {
                Timber.e("Error $it")
            })
    }

    fun moveTest2Page() {
//        movePage(MovePageEvent(
//            MvvmLifecycleTest2Activity::class.java,
//            bundle = Bundle().apply {
//                putLong(IntentKey.NOW_TIME, System.currentTimeMillis())
//            }
//        ))
    }
}