package com.features.compose_ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hmju.core.compose.TilTheme
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
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun InitContents() {
        var snackBarVisible by remember { mutableStateOf(false) }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text(text = "SnackBar Example", style = TilTheme.text.h2)})
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
            style = TilTheme.text.h1,
            modifier = Modifier
                .clickable { click() }
        )
    }
}