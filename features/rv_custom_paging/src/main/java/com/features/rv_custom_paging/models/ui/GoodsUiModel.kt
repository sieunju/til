package com.features.rv_custom_paging.models.ui

import com.features.rv_custom_paging.R
import com.features.rv_custom_paging.viewholder.GoodsViewHolder
import com.hmju.core.ui.base.BaseUiModel
import com.hmju.core.ui.viewholders.BaseViewHolder
import kotlin.reflect.KClass

/**
 * Description :
 *
 * Created by juhongmin on 2/15/24
 */
data class GoodsUiModel(
    val item: GoodsModel
) : BaseUiModel(R.layout.vh_goods) {
    override fun getClassName(): String {
        return "GoodsOneUiModel"
    }

    override fun areItemsTheSame(diffItem: Any): Boolean {
        return if (diffItem is GoodsUiModel) {
            item.id == diffItem.item.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(diffItem: Any): Boolean {
        return if (diffItem is GoodsUiModel) {
            item == diffItem.item
        } else {
            false
        }
    }

    override fun getViewHolderType(): KClass<out BaseViewHolder<*>> {
        return GoodsViewHolder::class
    }
}
