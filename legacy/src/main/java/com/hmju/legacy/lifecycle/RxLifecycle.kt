package com.hmju.legacy.lifecycle

import io.reactivex.rxjava3.disposables.Disposable

/**
 * Description : ViewModel 에서 Activity or Fragment Lifecycle
 * 에 대한 처리를 할 수 있도록 도와주는 클래스
 *
 * Created by juhongmin on 2022/02/27
 */
typealias DisposableFunction = () -> Disposable

@Suppress("unused")
fun onInit(function: DisposableFunction): RxLifecycle = RxLifecycle().init(function)

@Suppress("unused")
fun onVisible(function: DisposableFunction): RxLifecycle = RxLifecycle().visible(function)

class RxLifecycle : LifecycleObserver {
    private var onInit: DisposableFunction? = null
    private var initDisposable: Disposable? = null
    private var onVisible: DisposableFunction? = null
    private var visibleDisposable: Disposable? = null

    fun init(init: DisposableFunction): RxLifecycle {
        this.onInit = init
        return this
    }

    fun visible(visible: DisposableFunction): RxLifecycle {
        this.onVisible = visible
        return this
    }

    override fun onInit() {
        initDisposable = this.onInit?.invoke()
    }

    override fun onVisible() {
        visibleDisposable = this.onVisible?.invoke()
    }

    override fun onInvisible() {
        if (visibleDisposable?.isDisposed == false) {
            visibleDisposable?.dispose()
        }
    }

    override fun onRelease() {
        if (initDisposable?.isDisposed == false) {
            initDisposable?.dispose()
        }
    }
}