package com.hmju.compose_permissions_result.screen

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hmju.core.compose.TilTheme
import timber.log.Timber

/**
 * Description :
 *
 * Created by juhongmin on 4/3/24
 */
@Preview
@Composable
fun PermissionScreen(
    viewModel: PermissionViewModel = hiltViewModel<PermissionViewModel>()
) {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        Timber.d("Result ${result}")
    }

    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Compose 권한 팝업 입니다.",
            style = TilTheme.text.h3,
            color = TilTheme.color.white,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(TilTheme.color.blue)
        )

        Text(
            text = "권한 버튼 클릭",
            style = TilTheme.text.h3,
            color = TilTheme.color.white,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(TilTheme.color.purple)
                .clickable {
                    launcher.launch(permissions)
                }
        )
    }
}