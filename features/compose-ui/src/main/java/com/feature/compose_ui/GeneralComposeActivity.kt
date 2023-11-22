package com.feature.compose_ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Description : DataBinding 없이 일반적인 Compose Activity
 *
 * Created by juhongmin on 2023/08/22
 */
class GeneralComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { InitContents() }
    }

    // @Preview
    @Composable
    private fun InitContents() {
        var snackBarVisible by remember { mutableStateOf(false) }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text(text = "SnackBar Example", style = JTheme.h2)})
            }
        ) { it.calculateBottomPadding()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Hello World")
                ClickableText("여기가 클릭") {
                    snackBarVisible = true
                }
            }

            if (snackBarVisible) {
                Timber.d("오잉 켜집니까?")
                Snackbar(
                    action = {
                        Button(
                            onClick = { snackBarVisible = false },
                            content = { Text(text = "Dismiss") })
                    }
                ) {
                    Text(text = "클릭 클릭")
                }
            }
        }
    }

    @Composable
    private fun ClickableText(
        text: String,
        click: () -> Unit
    ) {
        Text(
            text = text,
            style = JTheme.h1,
            modifier = Modifier
                .clickable { click() }
        )
    }
}