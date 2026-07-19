package com.hmju.core_navigator

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultLauncher

/**
 * Description : Navigator API
 *
 * Created by juhongmin on 2025. 11. 2.
 */
interface Navigator {
    fun navigate(context: Context, uri: Uri, launcher: ActivityResultLauncher<Intent>? = null)
}
