package com.features.activity_result.webbridge

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import com.hmju.core.R
import com.hmju.core.compose.TilTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description : Web → App JavascriptInterface 예제
 * - execute()        : WorkerThread (JavascriptInterface 규약)
 * - completeCallback : MainThread  (Channel → receiveAsFlow → LaunchedEffect collect)
 *
 * Created by juhongmin on 2026. 4. 26.
 */
@AndroidEntryPoint
class WebBridgeActivity : AppCompatActivity() {

    private var bridgeCommand: WebBridgeActionCommand? = null

    private val activityResultCallback = registerForActivityResult(
	ActivityResultContracts.StartActivityForResult()
    ) { result ->
	bridgeCommand?.postActivityResult(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	setContent {
	    MaterialTheme {
		Surface(
		    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.statusBars),
		    color = TilTheme.color.white
		) { InitContents() }
	    }
	}
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun InitContents() {
	var command by remember { mutableStateOf<WebBridgeActionCommand?>(null) }
	var webView by remember { mutableStateOf<WebView?>(null) }

	LaunchedEffect(command) {
	    command?.consumeAsFlow()?.collect { (router, result) ->
		webView?.let { router.completeCallback(it, result) }
	    }
	}

	DisposableEffect(Unit) {
	    onDispose { command?.close() }
	}

	Scaffold(
	    topBar = {
		TopAppBar(
		    title = { Text("WebBridge") },
		    navigationIcon = {
			IconButton(onClick = { finish() }) {
			    Icon(
				painter = painterResource(R.drawable.ic_arrow_left),
				contentDescription = "back"
			    )
			}
		    }
		)
	    }
	) { innerPadding ->
	    AndroidView(
		factory = { ctx ->
		    buildWebView(ctx) { wv, cmd ->
			webView = wv
			command = cmd
			bridgeCommand = cmd
		    }
		},
		modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
	    )
	}
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun buildWebView(
	ctx: android.content.Context,
	onReady: (WebView, WebBridgeActionCommand) -> Unit
    ): WebView {
	return WebView(ctx).apply {
	    settings.javaScriptEnabled = true
	    val cmd = WebBridgeActionCommand(this).also {
		it.register(AlertWebActionRouter())
		it.register(
		    EditTextWebActionRouter(
			activityResultCallback,
			it.activityResultEventBus
		    )
		)
	    }
	    onReady(this, cmd)
	    loadUrl("file:///android_asset/web_bridge_demo.html")
	}
    }
}
