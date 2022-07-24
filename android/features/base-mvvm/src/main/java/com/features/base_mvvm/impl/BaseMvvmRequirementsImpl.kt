package com.features.base_mvvm.impl

import android.content.Context
import android.content.Intent
import com.features.base_mvvm.RefactorBaseTestActivity
import com.features.base_mvvm_requirements.BaseMvvmRequirements
import com.hmju.core.IntentKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Description : Base Mvvm Lifecycle 구현체 클래스
 *
 * Created by juhongmin on 2022/07/24
 */
internal class BaseMvvmRequirementsImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseMvvmRequirements {
    override fun moveToBaseMvvm() {
        Intent(context, RefactorBaseTestActivity::class.java).apply {
            putExtra(IntentKey.TOKEN, "randomToken")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(this)
        }
    }
}
