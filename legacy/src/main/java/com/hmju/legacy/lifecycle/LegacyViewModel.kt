package com.hmju.legacy.lifecycle

import android.Manifest
import android.os.Bundle
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.ConcurrentHashMap

/**
 * Description :
 *
 * Created by juhongmin on 2/10/24
 */
class LegacyViewModel : ViewModel() {


    companion object {
        @Volatile // 무슨일이 있어도 앱 실행중에는 메모리가 삭제되어서는 안된다.
        var cachePermissionMap = ConcurrentHashMap<String, Int>()
    }

    protected val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    /**
     * onPermissionResult 에 대한 처리
     * @param resultPermissionMap 전달 받은 권한 리턴 맵
     */
    fun performPermissionResult(resultPermissionMap: Map<String, Boolean>) {
        Flowable.fromIterable(javaClass.methods.toList())
            .filter { it.isAnnotationPresent(OnPermissionResult::class.java) }
            .map { method ->
                val map = mutableMapOf<String, Boolean>()
                method.getAnnotation(OnPermissionResult::class.java)?.let { annotation ->
                    annotation.permissions.forEach { permission ->
                        resultPermissionMap[permission]?.let { isGranted ->
                            map[permission] = isGranted
                        }
                    }
                }
                return@map map to method
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { pair ->
                if (pair.first.isNotEmpty()) {
                    pair.second.invoke(this, pair.first)
                }
            }
            .subscribe().addTo(compositeDisposable)
    }

    /**
     * 데이터 리스트 안에 유효하지 않는 권한에 대해서 필터링 처리 해주는 함수
     * @param list 검증되지 않은 권한 값
     */
    private fun validatePermission(list: List<String>): List<String> {
        val permissionList = mutableListOf<String>()
        // 권한을 가지고 있는 필드 값들은 앱실행후 한번만 요청하고 처리하도록
        if (cachePermissionMap.size == 0) {
            val permissionClass = Manifest.permission::class.java.newInstance()
            val groupClass = Manifest.permission_group::class.java.newInstance()

            Manifest.permission::class.java.fields.forEach {
                if (!it.isAnnotationPresent(Deprecated::class.java) && it.get(permissionClass) is String) {
                    cachePermissionMap[it.get(permissionClass) as String] = 0
                }
            }

            Manifest.permission_group::class.java.fields.forEach {
                if (!it.isAnnotationPresent(Deprecated::class.java) && it.get(groupClass) is String) {
                    cachePermissionMap[it.get(groupClass) as String] = 0
                }
            }
        }

        // Timber.d("Permission Map $cachePermissionMap")

        list.forEach { str ->
            if (cachePermissionMap.containsKey(str)) {
                permissionList.add(str)
            }
        }
        return permissionList
    }


    /**
     * [OnCreated], [OnResumed], [OnStopped], [OnViewCreated]
     *
     * 선언된 함수를 실행 하는 함수
     */
    @Deprecated("테스트 결과 별로 좋지 않는 방향이라 Deprecated 합니다.")
    inline fun <reified T : Annotation> performLifecycle(): Disposable {
        return Flowable.fromIterable(javaClass.methods.toList())
            .filter { it.isAnnotationPresent(T::class.java) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.invoke(this)
            }, {})
    }

    /**
     * onActivityResult 에 대한 처리
     * ReactiveX 타입
     * @param reqCode RequestCode
     * @param resCode ResultCode
     * @param data 전달 받을 데이터
     */
    fun handleActivityResult(reqCode: Int, resCode: Int, data: Bundle?): Disposable {
        return Flowable.fromIterable(javaClass.methods.toList())
            .filter { it.isAnnotationPresent(OnActivityResult::class.java) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { method ->
                method.getAnnotation(OnActivityResult::class.java)?.let { annotation ->
                    // RequestCode, RESULT Code 와 같은 함수만 호출
                    if (annotation.requestCode == reqCode && annotation.resCode == resCode) {
                        method.invoke(this, data)
                    }
                }
            }.subscribe()
    }
}