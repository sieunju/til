package com.features.rv_simple_like.models.ui

import com.features.rv_simple_like.R
import com.features.rv_simple_like.viewholders.SimpleLike4ViewHolder
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.viewholders.BaseViewHolder
import kotlin.reflect.KClass

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
data class Simple4UiModel(
    val model: GoodsModel
) : BaseUiModel(R.layout.vh_simple_like_4) {
    override fun getClassName(): String {
        return "Simple4UiModel"
    }

    override fun areItemsTheSame(diffItem: Any): Boolean {
        return if (diffItem is Simple1UiModel) {
            model.id == diffItem.model.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(diffItem: Any): Boolean {
        return if (diffItem is Simple1UiModel) {
            model == diffItem.model
        } else {
            false
        }
    }

    override fun getViewHolderType(): KClass<out BaseViewHolder<*>> {
        return SimpleLike4ViewHolder::class
    }

    val imagePath: String get() = model.imagePath
    val title: String get() = model.title
}
