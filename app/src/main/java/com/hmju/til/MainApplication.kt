package com.hmju.til

import android.app.Application
import com.http.tracking.TrackingManager
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import timber.log.Timber
import java.io.IOException
import java.net.SocketException

@HiltAndroidApp
open class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initTimber()

        // Rx Exception 처리
        initRxJava()

        // Shake Tracking
        initTracking()
    }

    /**
     * reactivex.exceptions.UndeliverableException 처리 함수.
     */
    private fun initRxJava() {
        // reactivex.exceptions.UndeliverableException
        // 참고 링크 https://thdev.tech/android/2019/03/04/RxJava2-Error-handling/
        RxJavaPlugins.setErrorHandler { e ->
            var error = e
            if (error is UndeliverableException) {
                error = e.cause ?: Throwable("UndeliverableException")
            }
            if (error is IOException || error is SocketException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (error is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if (error is NullPointerException || error is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler?.uncaughtException(
                    Thread.currentThread(),
                    error
                )
                return@setErrorHandler
            }
            if (error is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler?.uncaughtException(
                    Thread.currentThread(),
                    error
                )
                return@setErrorHandler
            }
        }
    }

    private fun initTimber() {
//        if (BuildConfig.DEBUG) {
        Timber.plant(object : Timber.DebugTree() {

            override fun createStackElementTag(element: StackTraceElement): String {
                val str = StringBuilder("JTimber")
                try {
                    str.append(
                        element.className
                            .substringAfterLast(".")
                            .substringBefore("$")
                    )
                    str.append(":")
                    str.append(element.methodName.substringAfterLast("."))
                } catch (ex: Exception) {
                }
                return str.toString()
            }
        })
//        }
    }

    private fun initTracking() {
        TrackingManager.getInstance()
            .setBuildType(true)
            .setWifiShare(true)
            .build(this)
    }
}