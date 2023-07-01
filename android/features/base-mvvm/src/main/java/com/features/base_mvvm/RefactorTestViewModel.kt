package com.features.base_mvvm

import android.Manifest
import android.app.Activity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.features.base_mvvm.ui.mvvm_lifecycle.MvvmLifecycleTestActivity
import com.hmju.core.model.test.SerializableEntity
import com.hmju.core.ui.base.ActivityResult
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.IntentKey
import com.hmju.core.ui.base.RxPermissionEvent
import com.hmju.core.ui.lifecycle.OnActivityResult
import com.hmju.core.ui.lifecycle.OnCreated
import com.hmju.core.ui.lifecycle.OnIntent
import com.hmju.core.ui.lifecycle.OnPermissionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/03/19
 */
@HiltViewModel
class RefactorTestViewModel @Inject constructor(
) : ActivityViewModel() {

    private val _title: MutableLiveData<String> by lazy { MutableLiveData() }
    val title: LiveData<String> get() = _title
    private val _contents: MutableLiveData<String> by lazy { MutableLiveData() }
    val contents: LiveData<String> get() = _contents

    @OnIntent
    fun intentData() {
        Timber.d("[s] onCreate Intent Data ===============================================")
        savedStateHandle.keys().forEach {
            Timber.d("Key $it Value ${savedStateHandle.get<Any>(it)}")
        }
        Timber.d("[s] onCreate Intent Data ===============================================")
    }

    @OnCreated
    fun onCreate() {
        _title.value = savedStateHandle.get<String>(IntentKey.TOKEN) ?: run { "Data 가 없습니다.." }
    }

    fun onResult() {
        _startActivityPage.value = ActivityResult(
            requestCode = 300,
            targetActivity = MvvmLifecycleTestActivity::class,
            data = bundleOf(
                "Serializable" to SerializableEntity("testTitle", System.currentTimeMillis())
            )
        )
    }

    fun onPermission() {
//        _startActivityPage.value = ActivityResult(
//            targetActivity = MvvmLifecycleTest3Activity::class,
//            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP,
//            data = bundleOf(
//                "TIT" to "qwe"
//            )
//        )

        RxPermissionEvent.publish(
            listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
            )
        )
    }

    @OnPermissionResult([Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA])
    fun onStorageResult(map: Map<String, Boolean>) {
        Timber.d("Permission Map $map")
    }

    @OnActivityResult(3000, Activity.RESULT_OK)
    fun onActivityResult(data: Bundle?) {
        if (data == null) return

        Timber.d("[s] Result Data ==================================================")
        val builder = StringBuilder()
        data.keySet().forEach { key ->
            Timber.d("Key $key Value ${data.get(key)}")
            builder.append("Key $key Value ${data.get(key)}\n")
        }
        _contents.value = builder.toString()
        Timber.d("[e] Result Data ==================================================")
    }

    @OnActivityResult(3000, Activity.RESULT_CANCELED)
    fun onActivityResultFail(data: Bundle?) {
        if (data == null) return

        Timber.d("[s] Cancel Result Data ==================================================")
        val builder = StringBuilder()
        data.keySet().forEach { key ->
            Timber.d("Key $key Value ${data.get(key)}")
            builder.append("Key $key Value ${data.get(key)}\n")
        }
        _contents.value = builder.toString()
        Timber.d("[e] Cancel Result Data ==================================================")
    }
}
