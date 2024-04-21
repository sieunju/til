package com.features.compose_navigation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
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
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun LoginScreen(
    navigator: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    TilComponent.HeaderAndContents(
        title = "로그인",
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        backClick = { navigator.popBackStack() }
    ) {
        GlideImage(
            model = "https://avatars.githubusercontent.com/u/33802191?v=4",
            contentDescription = null,
            modifier = Modifier.padding(top = 30.dp, bottom = 30.dp)
        )
        val id = viewModel.id.collectAsMutableState()
        val pw = viewModel.password.collectAsMutableState()
        val isEnable = viewModel.loginEnable.collectAsState()
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
        Spacer(Modifier.height(10.dp))
        TilComponent.EditText(
            text = pw,
            labelText = "비밀번호",
            placeHolderText = "입력해주세요",
            focusBg = Modifier
                .fillMaxWidth()
                .border(2.dp, TilTheme.color.black, shape = RoundedCornerShape(6.dp)),
            unFocusBg = Modifier
                .fillMaxWidth()
                .border(2.dp, TilTheme.color.gray3, shape = RoundedCornerShape(6.dp))
        )
        Spacer(Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(6.dp))
                .then(
                    if (isEnable.value) {
                        Modifier.background(TilTheme.color.blue)
                    } else {
                        Modifier.background(TilTheme.color.gray4)
                    }
                )
                .clickable(enabled = isEnable.value) {
                    // 회원 가입 완료 화면 이동
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "로그인",
                style = TilTheme.text.h4_B,
                color = TilTheme.color.white
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.start()

    }
}

// @Preview(showBackground = true)
@Composable
private fun Example() {
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

