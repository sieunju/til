package com.features.base_mvvm.ui.mvvm_lifecycle

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm.usecase.GetGoodsUseCase
import com.hmju.core.login_manager.LoginManager
import com.hmju.core.models.params.PagingQueryParams
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.BaseActivity
import com.hmju.core.ui.base.IntentKey
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/27
 */
@HiltViewModel
class MvvmLifecycleTestViewModel @Inject constructor(
    private val loginManager: LoginManager,
    private val getGoodsUseCase: GetGoodsUseCase
) : ActivityViewModel() {

    val startMovePageEvent: MutableLiveData<Unit> by lazy { MutableLiveData() }

    private val _contents: MutableLiveData<String> by lazy { MutableLiveData() }
    val contents: LiveData<String> get() = _contents

    override fun onDirectCreate() {
        super.onDirectCreate()
        val queryMap = PagingQueryParams()
        getGoodsUseCase(queryMap)
            .doOnSuccess { println("List $it") }
            .subscribe().addTo(compositeDisposable)
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
    }

    fun movePage2Req222() {
        startMovePageEvent.value = null
    }

    override fun onActivityResult(reqCode: Int, resCode: Int, data: Bundle) {
        super.onActivityResult(reqCode, resCode, data)
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

    fun movePermission() {
        setResultSaveData(BaseActivity.RES_CODE, Activity.RESULT_OK)
    }

    override fun onDirectCreatedToResumed() {
        super.onDirectCreatedToResumed()
        testResumeOne()
        testResumeTwo()
        testOnStopped()
    }


    private fun testResumeOne() {
        Timber.d("resume One")
    }

    private fun testResumeTwo() {
        Timber.d("resume Two")
    }

    private fun testOnStopped() {
        Timber.d("stopped ")
    }
}
