package com.features.main

import android.os.Bundle
import com.hmju.core.ui.base.BaseActivity
import com.features.main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        intent.putExtra("KEY", "AAFEFEFEFE")
        super.onCreate(savedInstanceState)
    }
}
