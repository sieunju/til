package com.hmju.core.ui.base

import android.os.Bundle
import android.os.Looper
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.bumptech.glide.RequestManager
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.io.Serializable
import javax.inject.Inject

/**
 * Description : 액티비티 전용 ViewModel 클래스
 * 액티비티에서만 사용되는 공통 LiveData 들을 정의합니다.
 * Created by juhongmin on 2022/04/13
 */
@Suppress("unused")
@HiltViewModel
open class ActivityViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var savedStateHandle: SavedStateHandle

    private val _startActivityPage: MutableLiveData<ActivityResult> by lazy { MutableLiveData() }
    val startActivityPage: LiveData<ActivityResult> get() = _startActivityPage
    private val _startFinishEvent: MutableLiveData<Unit> by lazy { MutableLiveData() }
    val startFinishEvent: LiveData<Unit> get() = _startFinishEvent


    private var _requestManager: RequestManager? = null
    val requestManager: RequestManager get() = _requestManager!!

    /**
     * Activity 에서 onNewIntent 함수 호출할떄 호출되는 함수
     * 로직 동일성을 위해 onCreate 와 onNewIntent 에서 사용합니다.
     * ex.) 딥링크 처리시 사용하면 좋습니다.
     */
    open fun onIntent() {}

    fun getBundleData(): Bundle {
        val bundle = Bundle()
        savedStateHandle.keys().forEach { key ->
            when (val value = savedStateHandle.get<Any>(key)) {
                is String -> bundle.putString(key, value)
                is Int -> bundle.putInt(key, value)
                is Long -> bundle.putLong(key, value)
                is Double -> bundle.putDouble(key, value)
                is Boolean -> bundle.putBoolean(key, value)
                is Float -> bundle.putFloat(key, value)
                is Array<*>,
                is Parcelable,
                is Serializable -> {
                    putBundle(key, value, bundle)
                }
            }
        }
        return bundle
    }

    /**
     * 특정 Bundle 들을 저장하기 위한 함수
     * @param key Put Key
     * @param value Array, Parcelable, Serializable Type.
     * @param bundle 저장할 Bundle 데이터
     */
    private inline fun <reified T> putBundle(key: String, value: T, bundle: Bundle) {
        try {
            when (value) {
                is Array<*> -> bundle.putStringArray(key, savedStateHandle.get<Array<String>>(key))
                is Parcelable -> bundle.putParcelable(key, savedStateHandle.get(key))
                is Serializable -> bundle.putSerializable(key, savedStateHandle.get(key))
            }
        } catch (ex: Exception) {
            Timber.e("ERROR $ex")
        }
    }


    /**
     * Activity, Fragment Lifecycle Annotation 로 처리하는게 아닌
     * 직접적으로 처리해야 하는 경우 해당 함수 호출합니다.
     * ex.) View 단에서 TabLayout 를 셋팅 해야 하는경우
     */
    open fun onDirectCreate() {}

    /**
     * Activity Result 관련 Data Set
     */
    fun setResultSaveData(key: String, value: Any) {
        savedStateHandle[key] = value
    }

    /**
     * Activity getIntentData 함수
     * @return NonNull
     */
    protected inline fun <reified T> getIntentData(key: String, default: T): T {
        return savedStateHandle.get<T>(key) ?: default
    }

    /**
     * Fragment getIntentData 함수
     * @return Nullable
     */
    protected inline fun <reified T> getIntentData(key: String): T? {
        return savedStateHandle.get<T>(key)
    }

    /**
     * 페이지 이동 관련 확장 함수
     * @sample
     * ActivityResult.Builder().moveToPage()
     *
     */
    protected fun ActivityResult.Builder.movePage() {
        val page = this.build()
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                _startActivityPage.value = page
            } else {
                _startActivityPage.postValue(page)
            }
        } catch (ex: Exception) {
            _startActivityPage.postValue(page)
        }
    }

    /**
     * SavedStateHandle 사용할수 있는 상태인지 체크하는 함수
     * @return true 사용가능한 상태, false 사용 불가능한 상태
     */
    fun checkBundleEnable(): Boolean {
        return this::savedStateHandle.isInitialized
    }

    /**
     * Glide RequestManager 초기화
     */
    fun initRequestManager(requestManager: RequestManager) {
        _requestManager = requestManager
    }

    /**
     * onDestroyView 상태일때 RequestManager 메모리 해제 하는 함수
     */
    fun clearRequestManager() {
        _requestManager = null
    }

    /**
     * Activity Page Finish
     * 공통 처리함수
     */
    fun onPageFinish() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            _startFinishEvent.value = Unit
        } else {
            _startFinishEvent.postValue(Unit)
        }
    }
}
