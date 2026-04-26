package com.features.activity_result.webbridge

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import com.features.activity_result.Constants
import com.features.activity_result.result_callback.EditTextActivity
import com.features.activity_result.result_callback.EditTextActivity.Companion.PAGE_TAG
import com.hmju.core_navigator.WebAction
import com.hmju.core_navigator.WebActionResult
import com.hmju.core_navigator.WebActionRouter
import io.reactivex.rxjava3.core.Flowable
import java.util.concurrent.TimeUnit

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
    private val activityResultEventBus: Flowable<ActivityResult>
) : WebActionRouter() {

    override val action: WebAction = WebAction.EDIT_TEXT

    // WorkerThread
    override fun execute(webView: WebView, params: Map<String, String>): WebActionResult {
	val callbackFn = params["script"] ?: return WebActionResult.Skip

	// WorkerThread → MainThread: Activity 실행 위임
	// webView.post{}는 View detach 시 실행 보장 안 됨 → MainLooper 직접 사용
	Handler(Looper.getMainLooper()).post {
	    launcher.launch(Intent(webView.context, EditTextActivity::class.java))
	}

	// WorkerThread 블로킹 — RxJava PublishSubject 첫 번째 이벤트 대기
	val result = try {
	    activityResultEventBus
		.filter { it.data?.getStringExtra(Constants.KEY_TYPE) == PAGE_TAG }
		.take(1)
		.timeout(30, TimeUnit.SECONDS)
		.blockingFirst()
	} catch (ex: Exception) {
	    return WebActionResult.Callback(
		dataMap = mapOf(
		    "callback" to callbackFn,
		    "action" to "edit_text",
		    "message" to "시간내에 입력하지 못했습니다. 다시 이용해주세요."
		)
	    )
	}
	if (result.resultCode != Activity.RESULT_OK) return WebActionResult.Callback(
	    dataMap = mapOf(
		"callback" to callbackFn,
		"action" to "edit_text",
		"message" to "사용자가 취소했습니다."
	    )
	)
	val text = result.data?.getStringExtra(Constants.KEY_EDIT).orEmpty()
	return WebActionResult.Callback(
	    dataMap = mapOf(
		"callback" to callbackFn,
		"action" to "edit_text",
		"message" to text
	    )
	)
    }

    // MainThread: onResult(action, message) 형태로 JS 호출
    override fun completeCallback(webView: WebView, result: WebActionResult.Callback) {
	val callback = result.dataMap["callback"] ?: return
	val action = result.dataMap["action"].orEmpty()
	val message = result.dataMap["message"].orEmpty()
	webView.evaluateJavascript("$callback('$action', '$message')") {}
    }
}
