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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hmju.core.compose.TilComponent
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.collectAsMutableState
import timber.log.Timber

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

    TilComponent.HeaderAndContents(
        title = "회원 가입",
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        backClick = { navigator.popBackStack() },
        scrollState = scrollState
    ) {
        TilComponent.ImageLoader(
            imageUrl = "https://til.qtzz.synology.me/resources/img/20240423/1713882085205.png",
            modifier = Modifier
                .size(300.dp, 300.dp)
                .padding(30.dp)
                .clip(RoundedCornerShape(150.dp))
        )
        TilComponent.EditText(
            text = id,
            labelText = "아이디",
            placeHolderText = "가입하고자 하는 아이디 입력해주세요.",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            nextAction = FocusDirection.Next
        )
        Spacer(Modifier.height(20.dp))
        TilComponent.EditText(
            text = pw,
            labelText = "비밀번호",
            placeHolderText = "가입하고자 하는 비밀번호 입력해주세요.",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            nextAction = FocusDirection.Next
        )
        Spacer(Modifier.height(5.dp))
        TilComponent.EditText(
            text = pwConfirm,
            labelText = "비밀번호 확인",
            placeHolderText = "가입하고자 하는 비밀번호 재입력해주세요.",
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
                    Timber.d("회원 가입 클릭!")
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