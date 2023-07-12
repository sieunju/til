package com.feature.async_migrate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.feature.async_migrate.databinding.ActivityAsyncMigrateBinding
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AsyncMigrateActivity : BaseActivity<ActivityAsyncMigrateBinding,AsyncMigrateActivityViewModel>(
    R.layout.activity_async_migrate
) {
    override val viewModel: AsyncMigrateActivityViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

}