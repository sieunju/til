package com.hmju.core_navigator

import android.content.Context
import android.net.Uri

/**
 * Description : Navigator API
 *
 * Created by juhongmin on 2025. 11. 2.
 */
interface Navigator {
    fun navigate(context: Context, uri: Uri)
}
