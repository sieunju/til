package com.features.async_migrate

import com.features.async_migrate.databinding.ActivityAsyncMigrateBinding
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AsyncMigrateActivity :
    BaseActivity<ActivityAsyncMigrateBinding, AsyncMigrateActivityViewModel>(
        R.layout.activity_async_migrate
    ) {

    override val viewModel: AsyncMigrateActivityViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

}
