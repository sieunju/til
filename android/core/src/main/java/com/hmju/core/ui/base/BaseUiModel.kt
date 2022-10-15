package com.hmju.core.ui.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.hmju.core.ui.viewholders.BaseViewHolder

abstract class BaseUiModel(@LayoutRes val layoutId: Int) {

    abstract fun getClassName(): String

    abstract fun areItemsTheSame(diffItem: Any): Boolean

    abstract fun areContentsTheSame(diffItem: Any): Boolean

    open fun createViewHolder(viewGroup: ViewGroup,vararg objs: Any?) : BaseViewHolder<*> {
        throw IllegalArgumentException("Invalid ViewHolder")
    }
}