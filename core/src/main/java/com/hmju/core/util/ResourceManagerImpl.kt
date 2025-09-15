package com.hmju.core.util

import android.content.Context
import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 6/22/24
 */
internal class ResourceManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceManager {
    override fun getRes(): Resources {
        return context.resources
    }

    override fun getColor(@ColorRes color: Int): Int {
        return ContextCompat.getColor(context, color)
    }

    override fun getString(@StringRes resId: Int): CharSequence {
        return context.resources.getString(resId)
    }

    override fun getString(resId: Int, vararg args: Any): CharSequence {
        return context.resources.getString(resId, args)
    }
}