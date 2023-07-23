package com.feature.compose_ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.feature.compose_ui.databinding.AComposeUiBinding
import com.hmju.core.ui.base.BaseActivity

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
        binding.vCompose.setContent {
            TestMessageCard()
        }
    }

    @OptIn(ExperimentalUnitApi::class)
    @Preview
    @Composable
    fun TestMessageCard() {
        Column {
            Text(
                text = "Hello Compose",
                fontSize = TextUnit(22.0F, TextUnitType.Sp)
            )
            Text(
                text = "Hello Compose1111",
                fontSize = TextUnit(22.0F, TextUnitType.Sp),
                color = Color.Blue
            )
            Text(
                text = "Hello Compose2222",
                fontSize = TextUnit(14.0F, TextUnitType.Sp),
            )
        }
    }
}
