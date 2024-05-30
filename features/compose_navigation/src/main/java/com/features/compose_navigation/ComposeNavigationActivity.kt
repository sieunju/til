package com.features.compose_navigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.features.compose_navigation.screens.login.LoginScreen
import com.features.compose_navigation.screens.login.SignUpScreen
import com.features.compose_navigation.screens.memo.MemoScreen
import com.hmju.core.compose.TilTheme
import com.hmju.core.compose.addFocusCleaner
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description :
 *
 * Created by juhongmin on 4/6/24
 */
@AndroidEntryPoint
internal class ComposeNavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .addFocusCleaner(LocalFocusManager.current),
                    color = TilTheme.color.white
                ) {
                    InitNavigation()
                }
            }
        }
    }

    @Composable
    private fun InitNavigation(
        navController: NavHostController = rememberNavController()
    ) {
        NavHost(navController, Screens.SIGNUP.destination) {
            Screens.SIGNUP.getNavGraph(this) {
                SignUpScreen(navController)
            }
            Screens.LOGIN.getNavGraph(this) {
                LoginScreen(navController)
            }
            Screens.MEMO.getNavGraph(this) {
                MemoScreen(navController)
            }
        }
    }
}
