package com.features.compose_navigation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hmju.core.compose.TilComponent
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.addFocusCleaner
import com.hmju.core.compose.collectAsMutableState
import timber.log.Timber

/**
 * Description : 로그인 화면
 *
 * Created by juhongmin on 4/7/24
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navigator: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .addFocusCleaner(LocalFocusManager.current)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "로그인",
                style = TilTheme.text.h2_B
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .padding(20.dp)
                .wrapContentSize()
        ) {
            val id = viewModel.id.collectAsMutableState()
            val pw = viewModel.password.collectAsMutableState()
            TilComponent.EditText(
                text = id,
                labelText = "이름",
                placeHolderText = "입력해주세요.",
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                nextAction = FocusDirection.Next,
                focusBg = Modifier
                    .fillMaxWidth()
                    .border(2.dp, TilTheme.color.black, shape = RoundedCornerShape(15.dp)),
                unFocusBg = Modifier
                    .fillMaxWidth()
                    .border(2.dp, TilTheme.color.gray3, shape = RoundedCornerShape(15.dp))
            )
            Spacer(modifier = Modifier.height(10.dp))
            TilComponent.EditText(
                text = pw,
                labelText = "비밀번호",
                placeHolderText = "입력해주세요",
                focusBg = Modifier
                    .fillMaxWidth()
                    .border(2.dp, TilTheme.color.black, shape = RoundedCornerShape(15.dp)),
                unFocusBg = Modifier
                    .fillMaxWidth()
                    .border(2.dp, TilTheme.color.gray3, shape = RoundedCornerShape(15.dp))
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.start()
    }
}

// @Preview(showBackground = true)
@Composable
private fun Example(){
    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .addFocusCleaner(LocalFocusManager.current),
            color = TilTheme.color.white
        ) {
            LoginScreen(navigator = rememberNavController())
        }
    }
}

