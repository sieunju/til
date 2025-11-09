package com.hmju.core_navigator

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Description : BaseClass Inject 하도록 하기 위한 Interface
 *
 * Created by juhongmin on 2025. 11. 9.
 */
@EntryPoint
@InstallIn(ActivityComponent::class)
interface NavigatorEntryPoint {
    fun navigator(): Navigator
}