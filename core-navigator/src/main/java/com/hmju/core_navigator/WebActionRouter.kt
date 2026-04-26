package com.hmju.core_navigator

import android.webkit.WebView
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import timber.log.Timber

/**
 * Description : WebView JavaScriptInterface
 *
 * Created by juhongmin on 2026. 4. 26.
 */
abstract class WebActionRouter {

    abstract val action: WebAction

    @WorkerThread
    abstract fun execute(
	webView: WebView,
	params: Map<String, String>
    ): WebActionResult

    @MainThread
    open fun completeCallback(webView: WebView, result: WebActionResult.Callback) {
	// 필요할때만 재정의해서 처리합니다.
	val script = result.dataMap["script"] ?: return
	webView.evaluateJavascript(script) {
	    Timber.d("evaluateJavascript Callback ${it}")
	}
    }
}