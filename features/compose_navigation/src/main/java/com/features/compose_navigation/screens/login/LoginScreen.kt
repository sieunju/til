package com.features.compose_navigation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hmju.core.compose.TilTheme
import kotlinx.coroutines.flow.asStateFlow
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
    Timber.d("LoginScreen Start")
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
            ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "로그인",
                style = TilTheme.text.h2_B
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            val id = viewModel.id.collectAsState()
            TextField(value = id.value, onValueChange = {
                Timber.d("State $it")
            })
        }
    }
}