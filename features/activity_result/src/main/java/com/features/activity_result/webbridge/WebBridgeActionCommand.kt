package com.features.activity_result.webbridge

import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.activity.result.ActivityResult
import com.hmju.core_navigator.WebActionResult
import com.hmju.core_navigator.WebActionRouter
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.concurrent.Executors

/**
 * Description :
 *
 * Created by juhongmin on 2026. 4. 26.
 */
internal class WebBridgeActionCommand(
    private val webView: WebView
) {
    private val processor: MutableSet<WebActionRouter> by lazy { mutableSetOf() }

    // WorkerThread → MainThread: completeCallback 전달용 Channel
    private val channel = Channel<Pair<WebActionRouter, WebActionResult.Callback>>(Channel.UNLIMITED)

    // ActivityResult EventBus (RxJava PublishSubject)
    // execute() 블로킹 해제 용도 — 외부에서 postActivityResult()로만 접근
    private val activityResultSubject = PublishSubject.create<ActivityResult>()
    val activityResultObservable = activityResultSubject.hide()

    private val executor = Executors.newCachedThreadPool()

    private val jsonFormat = Json {
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    @Serializable
    data class WebActionBridge(
        val action: String = "",
        val params: Map<String, String> = mapOf()
    )

    init {
        webView.addJavascriptInterface(this, "Android")
    }

    @JavascriptInterface
    fun execute(script: String?) {
        if (script.isNullOrEmpty()) return
        runCatching {
            val data: WebActionBridge = jsonFormat.decodeFromString(script)
            for (router in processor) {
                if (router.action.key != data.action) continue
                executor.submit {
                    val result = router.execute(webView, data.params)
                    if (result is WebActionResult.Callback) {
                        channel.trySend(router to result)
                    }
                }
            }
        }
    }

    fun postActivityResult(result: ActivityResult) {
        activityResultSubject.onNext(result)
    }

    fun consumeAsFlow() = channel.receiveAsFlow()

    fun register(webActionRouter: WebActionRouter) {
        processor.add(webActionRouter)
    }

    fun close() {
        channel.close()
        executor.shutdown()
    }
}
