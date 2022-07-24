package com.features.recyclerview

import android.os.Bundle
import com.features.recyclerview.databinding.ActivityRecyclerViewBinding
import com.hmju.core.ActivityViewModel
import com.hmju.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecyclerViewActivity :
    BaseActivity<ActivityRecyclerViewBinding, ActivityViewModel>(R.layout.activity_recycler_view) {

    override val viewModel: ActivityViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, RecyclerViewFragment())
            addToBackStack(null)
            commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}