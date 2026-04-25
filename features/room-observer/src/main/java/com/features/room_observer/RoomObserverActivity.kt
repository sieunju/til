package com.features.room_observer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.features.room_observer.screens.RoomObserverScreen
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.addFocusCleaner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class RoomObserverActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		enableEdgeToEdge()
		super.onCreate(savedInstanceState)
		setContent {
			MaterialTheme {
				Surface(
					modifier = Modifier
						.fillMaxSize()
						.windowInsetsPadding(WindowInsets.statusBars)
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
