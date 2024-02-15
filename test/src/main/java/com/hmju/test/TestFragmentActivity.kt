package com.hmju.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hmju.test.databinding.ATestFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFragmentActivity : AppCompatActivity() {

    private lateinit var binding: ATestFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(androidx.appcompat.R.style.Theme_AppCompat)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.a_test_fragment)
        binding.lifecycleOwner = this
    }
}