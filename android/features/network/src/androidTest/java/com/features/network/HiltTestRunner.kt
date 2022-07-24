package com.features.network

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

/**
 * Description : Hilt 전용 Test Runner
 *
 * Created by juhongmin on 2022/07/24
 */
class HiltTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, NetworkHiltTestApplication_Application::class.simpleName, context)
    }
}