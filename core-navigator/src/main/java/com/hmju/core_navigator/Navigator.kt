package com.hmju.core_navigator

import android.net.Uri
import androidx.annotation.MainThread

/**
 * Description : Navigator API
 *
 * Created by juhongmin on 2025. 11. 2.
 */
interface Navigator {
    @MainThread
    fun navigate(uri: Uri) : RouterResult
}
