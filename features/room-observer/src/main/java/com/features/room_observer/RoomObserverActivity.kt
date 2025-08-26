package com.features.room_observer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.features.room_observer.screens.RoomObserverScreen
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.addFocusCleaner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class RoomObserverActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		// 추후 화면에 따라 StatusBar 컨트롤
		ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
			insets
		}
		setContent {
			MaterialTheme {
				Surface(
					modifier = Modifier
						.fillMaxSize()
						.addFocusCleaner(LocalFocusManager.current),
					color = TilTheme.color.white
				) {
					RoomObserverScreen(
						closeClick = { finish() }
					)
				}
			}
		}
	}
}
