package com.features.activity_result.webbridge

import android.app.Activity
import android.content.Intent
import android.webkit.WebView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import com.features.activity_result.Constants
import com.features.activity_result.EditTextActivity
import com.hmju.core_navigator.WebAction
import com.hmju.core_navigator.WebActionResult
import com.hmju.core_navigator.WebActionRouter
import io.reactivex.rxjava3.core.Observable

/**
 * Description : 웹 → 앱 EditText 액션 처리
 *
 * execute()  : WorkerThread
 *   1. webView.post { launcher.launch } 로 MainThread에 Activity 실행 위임
 *   2. activityResultObservable.blockingFirst() 로 WorkerThread 블로킹 대기
 *   3. 결과 수신 후 콜백 스크립트 구성 → Callback 반환
 *
 * completeCallback() : MainThread (base 구현 — evaluateJavascript 호출)
 *
 * Created by juhongmin on 2026. 4. 26.
 */
internal class EditTextWebActionRouter(
    private val launcher: ActivityResultLauncher<Intent>,
    private val activityResultObservable: Observable<ActivityResult>
) : WebActionRouter() {

    override val action: WebAction = WebAction.EDIT_TEXT

    // WorkerThread
    override fun execute(webView: WebView, params: Map<String, String>): WebActionResult {
        val callbackFn = params["script"] ?: return WebActionResult.Skip

        // WorkerThread → MainThread: Activity 실행 위임
        webView.post {
            launcher.launch(Intent(webView.context, EditTextActivity::class.java))
        }

        // WorkerThread 블로킹 — RxJava PublishSubject 첫 번째 이벤트 대기
        val result = activityResultObservable.blockingFirst()

        if (result.resultCode != Activity.RESULT_OK) return WebActionResult.Skip

        val text = result.data?.getStringExtra(Constants.KEY_EDIT).orEmpty()
        return WebActionResult.Callback(
            dataMap = mapOf("script" to "$callbackFn('edit_text', '$text')")
        )
    }
}
