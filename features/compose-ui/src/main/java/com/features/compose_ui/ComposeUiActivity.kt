package com.features.compose_ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.features.compose_ui.BR
import com.features.compose_ui.R
import com.features.compose_ui.databinding.AComposeUiBinding
import com.hmju.core.compose.TilTheme
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
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Text(
                        text = "Hello H0",
                        style = TilTheme.text.h0,
                        modifier = Modifier.clickable {
                            val intent = Intent(this@ComposeUiActivity, GeneralComposeActivity::class.java)
                            this@ComposeUiActivity.startActivity(intent)
                        }
                    )
                    Text(
                        text = "Hello H1",
                        style = TilTheme.text.h1
                    )
                    Text(
                        text = "Hello H2",
                        style = TilTheme.text.h2
                    )
                    Text(
                        text = "Hello H3",
                        style = TilTheme.text.h3
                    )
                    Text(
                        text = "Hello h4",
                        style = TilTheme.text.h4
                    )
                    Text(
                        text = "Hello h5",
                        style = TilTheme.text.h5
                    )
                }
            }
        }
    }
}
