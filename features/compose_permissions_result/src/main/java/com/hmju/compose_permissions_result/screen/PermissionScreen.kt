package com.hmju.compose_permissions_result.screen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
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
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState = remember(lifecycleOwner) {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA
    )
    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycleState.value = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        var isAllGrated = true
        for ((_, value) in result) {
            if (!value) {
                isAllGrated = false
                break
            }
        }
        Timber.d("모든 권한 허용 $isAllGrated")
        if (!isAllGrated) {
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:${context.packageName}")
            ).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                addCategory(Intent.CATEGORY_DEFAULT)
                context.startActivity(this)
            }
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
                            .wrapContentHeight()
                            .clickable { launcher.launch(permissions) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "권한 버튼 클릭",
                            style = TilTheme.text.h3,
                            color = TilTheme.color.white,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(top = 50.dp, bottom = 50.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(TilTheme.color.purple)
                            .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        val state = lifecycleState.value
                        val msg = if (state == Lifecycle.Event.ON_RESUME) {
                            var isAllGrated = true
                            for (element in permissions) {
                                if (ContextCompat.checkSelfPermission(
                                        context,
                                        element
                                    ) == PackageManager.PERMISSION_DENIED
                                ) {
                                    isAllGrated = false
                                    break
                                }
                            }
                            if (isAllGrated) {
                                "모든 권한 허용"
                            } else {
                                "모든 권한 거부된 상태 입니다."
                            }
                        } else {
                            "권한 체크중"
                        }
                        Text(
                            text = "${msg}",
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
}