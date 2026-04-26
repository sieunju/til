package com.features.activity_result.webbridge

import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import com.hmju.core_navigator.WebAction
import com.hmju.core_navigator.WebActionResult
import com.hmju.core_navigator.WebActionRouter
import timber.log.Timber

/**
 * Description : 웹 → 앱 Alert 액션 처리
 * execute()          : WorkerThread — params에서 title, script 추출
 * completeCallback() : MainThread  — 네이티브 AlertDialog 표시 후 script 콜백
 *
 * Created by juhongmin on 2026. 4. 26.
 */
internal class AlertWebActionRouter : WebActionRouter() {

    override val action: WebAction = WebAction.ALERT

    // WorkerThread: title, script 추출 후 Callback 반환
    override fun execute(webView: WebView, params: Map<String, String>): WebActionResult {
        val script = params["script"] ?: return WebActionResult.Skip
        val title = params["title"].orEmpty()
        return WebActionResult.Callback(dataMap = mapOf("title" to title, "script" to script))
    }

    // MainThread: 네이티브 AlertDialog → 확인 시 evaluateJavascript 호출
    override fun completeCallback(webView: WebView, result: WebActionResult.Callback) {
        val title = result.dataMap["title"].orEmpty()
        val script = result.dataMap["script"] ?: return

        AlertDialog.Builder(webView.context)
            .setMessage(title)
            .setPositiveButton("확인") { _, _ ->
                webView.evaluateJavascript(script) {
                    Timber.d("evaluateJavascript callback $it")
                }
            }
            .show()
    }
}
