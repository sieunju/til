package com.hmju.compose_permissions_result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hmju.compose_permissions_result.screen.PermissionScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposePermissionsResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent { PermissionScreen() }
    }
}
