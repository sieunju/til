package com.hmju.core.util

import android.content.res.Resources
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

/**
 * Description : Android Resource Manager
 *
 * Created by juhongmin on 6/22/24
 */
interface ResourceManager {
    fun getRes(): Resources
    fun getColor(@ColorRes color: Int): Int
    fun getString(@StringRes resId: Int): CharSequence

    fun getString(@StringRes resId: Int, vararg args: Any): CharSequence

}