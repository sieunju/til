package com.hmju.core.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hmju.core.R

/**
 * Description : Compose TIL Component
 *
 * Created by juhongmin on 4/10/24
 */
object TilComponent {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @Composable
    fun EditText(
        text: MutableState<String>,
        labelText: String,
        placeHolderText: String,
        keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        nextAction: FocusDirection = FocusDirection.Exit,
        maxLines: Int = 1,
        focusBg: Modifier,
        unFocusBg: Modifier
    ) {
        var isFocused by remember { mutableStateOf(false) }
        val focusManager = LocalFocusManager.current
        val modifier = if (isFocused) focusBg else unFocusBg
        Box(
            modifier = Modifier
                .onFocusChanged { isFocused = it.isFocused }
                .then(modifier)
        ) {
            TextField(
                value = text.value,
                onValueChange = { text.value = it },
                textStyle = TilTheme.text.h3,
                label = {
                    if (isFocused || text.value.isNotEmpty()) {
                        Text(text = labelText, color = TilTheme.color.black)
                    } else {
                        Text(text = placeHolderText)
                    }
                },
                placeholder = { Text(text = placeHolderText) },
                keyboardOptions = keyboardOptions,
                keyboardActions = KeyboardActions { focusManager.moveFocus(nextAction) },
                singleLine = maxLines <= 1,
                maxLines = maxLines,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

//            if (isFocused) {
//                Box(modifier = focusBg)
//            } else {
//                Box(modifier = unFocusBg)
//            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HeaderAndContents(
        title: String,
        backClick: () -> Unit,
        modifier: Modifier = Modifier,
        contentAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
        content: @Composable ColumnScope.() -> Unit
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .fillMaxHeight()
                                .clickable { backClick() },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_arrow_left),
                                contentDescription = null
                            )
                        }
                        Text(
                            text = title,
                            style = TilTheme.text.h4_B,
                            modifier = Modifier.weight(1F),
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.width(50.dp))
                    }

                    Spacer(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(TilTheme.color.gray4)
                    )
                }


            }
        ) {
            Column(
                modifier = modifier
                    .padding(it),
                horizontalAlignment = contentAlignment
            ) { content() }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderAndContentsExample() {
    TilComponent.HeaderAndContents(
        "헤더입니다",
        backClick = {}
    ) {

    }
}
