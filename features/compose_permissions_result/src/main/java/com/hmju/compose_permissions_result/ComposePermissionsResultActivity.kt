package com.hmju.compose_permissions_result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.hmju.compose_permissions_result.screen.PermissionScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposePermissionsResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PermissionScreen() }
    }
}
