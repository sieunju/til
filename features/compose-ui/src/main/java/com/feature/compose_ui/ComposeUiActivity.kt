package com.feature.compose_ui

import android.os.Bundle
import androidx.activity.viewModels
import com.feature.compose_ui.databinding.AComposeUiBinding
import com.hmju.core.ui.base.BaseActivity
import timber.log.Timber

/**
 * Description : Compose UI 페이지
 *
 * Created by juhongmin on 2023/07/23
 */
class ComposeUiActivity :  BaseActivity<AComposeUiBinding,ComposeUiActivityViewModel> (R.layout.a_compose_ui){
    override val viewModel: ComposeUiActivityViewModel by viewModels()
    override val bindingVariable: Int = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate!!")
    }
}
