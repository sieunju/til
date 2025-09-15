package com.features.recyclerview

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.commit
import com.features.recyclerview.databinding.ActivityRecyclerViewBinding
import com.hmju.core.ui.base.ActivityViewModel
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecyclerViewActivity : BaseActivity<ActivityRecyclerViewBinding, ActivityViewModel>(
    R.layout.activity_recycler_view
) {

    override val viewModel: ActivityViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private val onBackPressCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (supportFragmentManager.backStackEntryCount > 1) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            replace(R.id.fragment, RecyclerViewFragment())
            addToBackStack(null)
        }
        onBackPressedDispatcher.addCallback(this, onBackPressCallback)
    }
}