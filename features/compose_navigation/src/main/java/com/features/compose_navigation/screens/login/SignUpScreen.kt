package com.features.compose_navigation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.features.compose_navigation.Screens
import com.hmju.core.compose.TilComponent
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.backPressed
import com.hmju.core.compose.collectAsMutableState

/**
 * Description : 회원 가입
 *
 * Created by juhongmin on 4/22/24
 */

@Composable
fun SignUpScreen(
    navigator: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val id = viewModel.id.collectAsMutableState()
    val pw = viewModel.password.collectAsMutableState()
    val pwConfirm = viewModel.passwordConfirm.collectAsMutableState()
    val isSignUpEnable = viewModel.isSignUpEnable.collectAsState()
    val scrollState = rememberScrollState()

    TilComponent.HeaderAndContentsColumn(
        title = "회원 가입",
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        backClick = { navigator.backPressed() },
        scrollState = scrollState
    ) {
        TilComponent.ImageLoader(
            imageUrl = "https://til.qtzz.synology.me/resources/img/20240507/1715084116936.png",
            modifier = Modifier
                .size(150.dp, 150.dp)
                .padding(30.dp)
                .clip(RoundedCornerShape(150.dp))
        )
        TilComponent.EditText(
            text = id,
            labelText = "아이디",
            placeHolderText = "아이디 입력",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            nextAction = FocusDirection.Next
        )
        Spacer(Modifier.height(20.dp))
        TilComponent.EditText(
            text = pw,
            labelText = "비밀번호",
            placeHolderText = "비밀번호 입력",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            nextAction = FocusDirection.Next
        )
        Spacer(Modifier.height(5.dp))
        TilComponent.EditText(
            text = pwConfirm,
            labelText = "비밀번호 확인",
            placeHolderText = "비밀번호 재 입력",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        Spacer(Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(6.dp))
                .then(
                    if (isSignUpEnable.value) {
                        Modifier.background(TilTheme.color.blue)
                    } else {
                        Modifier.background(TilTheme.color.gray4)
                    }
                )
                .clickable(isSignUpEnable.value) {
                    val route = Screens.LOGIN.getNavigation(
                        mapOf(
                            "user_id" to id.value,
                            "user_pw" to pw.value
                        )
                    )
                    navigator.navigate(route) {
//                        popUpTo(Screens.SIGNUP.destination) {
//                            inclusive = true
//                        }
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "회원 가입",
                style = TilTheme.text.h4_B,
                color = if (isSignUpEnable.value) TilTheme.color.white else TilTheme.color.black
            )
        }
        Spacer(Modifier.height(50.dp))
    }
}