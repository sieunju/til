package com.features.recyclerview.models

import com.features.recyclerview.R
import com.features.recyclerview.models.ui.GoodsModel
import com.features.recyclerview.ui.independent_viewholder.SimpleLike1ViewHolder
import com.features.recyclerview.ui.independent_viewholder.SimpleLike2ViewHolder
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.viewholders.BaseViewHolder
import kotlin.reflect.KClass

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/16
 */
data class GoodsOneUiModel(
    val model: GoodsModel
) : BaseUiModel(R.layout.vh_simple_like_recyclerview_1) {

    override fun getClassName() = "GoodsOneUiModel"

    override fun areItemsTheSame(diffItem: Any): Boolean {
        return if (diffItem is GoodsOneUiModel) {
            model.id == diffItem.model.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(diffItem: Any): Boolean {
        return if (diffItem is GoodsOneUiModel) {
            model == diffItem.model
        } else {
            false
        }
    }

    override fun getViewHolderType(): KClass<out BaseViewHolder<*>> {
        return SimpleLike1ViewHolder::class
    }
}

data class GoodsTwoUiModel(
    val model: GoodsModel
) : BaseUiModel(R.layout.vh_simple_like_recyclerview_2) {

    override fun getClassName() = "GoodsTwoUiModel"

    override fun areItemsTheSame(diffItem: Any): Boolean {
        return if (diffItem is GoodsTwoUiModel) {
            model.id == diffItem.model.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(diffItem: Any): Boolean {
        return if (diffItem is GoodsTwoUiModel) {
            model == diffItem.model
        } else {
            false
        }
    }

    override fun getViewHolderType(): KClass<out BaseViewHolder<*>> {
        return SimpleLike2ViewHolder::class
    }
}