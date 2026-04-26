package com.features.activity_result.result_callback

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.features.activity_result.Constants
import com.hmju.core.compose.TilComponent
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.addFocusCleaner
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Description :
 *
 * Created by juhongmin on 2026. 4. 26.
 */
@AndroidEntryPoint
internal class EditTextActivity : AppCompatActivity() {

    companion object {
	const val PAGE_TAG = "edit_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	setContent {
	    MaterialTheme {
		Surface(
		    modifier = Modifier.Companion
			.fillMaxSize()
			.windowInsetsPadding(WindowInsets.Companion.statusBars)
			.addFocusCleaner(LocalFocusManager.current),
		    color = TilTheme.color.white
		) { InitContents() }
	    }
	}
    }

    @Composable
    private fun InitContents() {
	val snackBarHostState = remember { SnackbarHostState() }
	val scope = rememberCoroutineScope()
	Box(modifier = Modifier.Companion.fillMaxSize()) {
	    TilComponent.HeaderAndContentsColumn(
		title = "EditTextAct",
		modifier = Modifier.Companion
		    .fillMaxSize()
		    .padding(start = 16.dp, end = 16.dp),
		backClick = {
		    setResult(
			RESULT_CANCELED, Intent()
			    .putExtra(Constants.KEY_TYPE, PAGE_TAG)
		    )
		    finish()
		}
	    ) {
		val text = remember { mutableStateOf("") }
		TilComponent.EditText(
		    text = text,
		    labelText = "텍스트입력",
		    placeHolderText = "입력해주세요."
		)
		Box(
		    modifier = Modifier.Companion
			.fillMaxSize()
			.height(50.dp)
			.clip(RoundedCornerShape(6.dp))
			.clickable {
			    if (text.value.isEmpty()) {
				scope.launch {
				    snackBarHostState.showSnackbar("텍스트를 입력해주세요.")
				}
				return@clickable
			    }
			    setResult(
				RESULT_OK, Intent()
				    .putExtra(Constants.KEY_TYPE, PAGE_TAG)
				    .putExtra(Constants.KEY_EDIT, text.value)
			    )
			    finish()
			},
		    contentAlignment = Alignment.Companion.Center
		) {
		    Text(text = "저장하고 나가기")
		}
	    }

	    SnackbarHost(
		hostState = snackBarHostState,
		modifier = Modifier.Companion.align(Alignment.Companion.BottomCenter)
	    )
	}
    }
}
