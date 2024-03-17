package com.hmju.core.ui.base

import androidx.annotation.LayoutRes
import com.hmju.core.ui.viewholders.BaseViewHolder
import kotlin.reflect.KClass

abstract class BaseUiModel(
    @LayoutRes val layoutId: Int
) {

    abstract fun getClassName(): String

    abstract fun areItemsTheSame(diffItem: Any): Boolean

    abstract fun areContentsTheSame(diffItem: Any): Boolean

    // abstract fun getViewHolderType(): KClass<out BaseViewHolder<*>>

    open fun getViewHolderType(): KClass<out BaseViewHolder<*>> {
        throw IllegalArgumentException("Invalid ViewHolder")
    }
}
