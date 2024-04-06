package com.hmju.compose_permissions_result.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hmju.core.compose.ComposeLifecycleState
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.rememberLifecycleUpdatedState

/**
 * Description :
 *
 * Created by juhongmin on 4/3/24
 */
// @Preview
@Composable
fun PermissionScreen(
    viewModel: PermissionViewModel = hiltViewModel<PermissionViewModel>()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState = rememberLifecycleUpdatedState(lifecycleOwner)
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        if (!viewModel.isAllGrated(result)) {
            viewModel.moveToSetting()
        }
    }

    val scrollState = rememberScrollState()

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(TilTheme.color.blue),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "권한 팝업 입니다.",
                        style = TilTheme.text.h3,
                        color = TilTheme.color.white,
                        textAlign = TextAlign.Center
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(TilTheme.color.purple)
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        val state = lifecycleState.value
                        val msg = if (state == ComposeLifecycleState.ON_RESUME) {
                            if (viewModel.isAllGrated(viewModel.getPermissionList().toList())) {
                                "모든 권한 허용"
                            } else {
                                "모든 권한 거부된 상태 입니다."
                            }
                        } else {
                            "권한 체크중"
                        }
                        Text(
                            text = msg,
                            style = TilTheme.text.h3,
                            color = TilTheme.color.white,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(top = 50.dp, bottom = 50.dp)
                        )
                    }
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        launcher.launch(viewModel.getPermissionList())
    }
}