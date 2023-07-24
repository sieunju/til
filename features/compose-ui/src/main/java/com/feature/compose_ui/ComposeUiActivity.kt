package com.feature.compose_ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.feature.compose_ui.databinding.AComposeUiBinding
import com.hmju.core.ui.base.BaseActivity
import timber.log.Timber

/**
 * Description : Compose UI 페이지
 *
 * Created by juhongmin on 2023/07/23
 */
class ComposeUiActivity :
    BaseActivity<AComposeUiBinding, ComposeUiActivityViewModel>(R.layout.a_compose_ui) {

    override val viewModel: ComposeUiActivityViewModel by viewModels()
    override val bindingVariable: Int = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vCompose.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        binding.vCompose.setContent {
            TestMessageCard()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
    }

    @Preview
    @Composable
    fun TestMessageCard() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .background(Color.Gray)
        ) {
            Text(
                text = "Hello H0",
                style = JTheme.h0
            )
            Text(
                text = "Hello H1",
                style = JTheme.h1
            )
            Text(
                text = "Hello H2",
                style = JTheme.h2
            )
            Text(
                text = "Hello H3",
                style = JTheme.h3
            )
            Text(
                text = "Hello h4",
                style = JTheme.h4
            )
            Text(
                text = "Hello h5",
                style = JTheme.h5
            )

        }
    }
}
