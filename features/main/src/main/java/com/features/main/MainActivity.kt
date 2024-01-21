package com.features.main

import android.os.Bundle
import com.features.main.databinding.ActivityMainBinding
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        intent.putExtra("KEY", "AAFEFEFEFE")
        super.onCreate(savedInstanceState)
    }
}
