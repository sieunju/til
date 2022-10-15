package com.features.recyclerview.model

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.hmju.core.ui.base.BaseUiModel
import com.features.recyclerview.R
import com.features.recyclerview.ui.independent_viewholder.SimpleLike1ViewHolder
import com.features.recyclerview.ui.independent_viewholder.SimpleLike2ViewHolder
import com.hmju.core.model.goods.GoodsEntity
import com.hmju.core.ui.viewholders.BaseViewHolder

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/16
 */
data class GoodsOneUiModel(
    val item: GoodsEntity
) : BaseUiModel(R.layout.vh_simple_like_recyclerview_1) {

    override fun getClassName() = "GoodsOneUiModel"

    override fun areItemsTheSame(diffItem: Any): Boolean {
        return if (diffItem is GoodsOneUiModel) {
            item.id == diffItem.item.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(diffItem: Any): Boolean {
        return if (diffItem is GoodsOneUiModel) {
            item == diffItem.item
        } else {
            false
        }
    }

    override fun createViewHolder(viewGroup: ViewGroup, vararg objs: Any?): BaseViewHolder<*> {
        return SimpleLike1ViewHolder(viewGroup)
    }
}

data class GoodsTwoUiModel(
    val item: GoodsEntity
) : BaseUiModel(R.layout.vh_simple_like_recyclerview_2) {

    override fun getClassName() = "GoodsTwoUiModel"

    override fun areItemsTheSame(diffItem: Any): Boolean {
        return if (diffItem is GoodsTwoUiModel) {
            item.id == diffItem.item.id
        } else {
            false
        }
    }

    override fun areContentsTheSame(diffItem: Any): Boolean {
        return if (diffItem is GoodsTwoUiModel) {
            item == diffItem.item
        } else {
            false
        }
    }

    override fun createViewHolder(viewGroup: ViewGroup, vararg objs: Any?): BaseViewHolder<*> {
        return SimpleLike2ViewHolder(viewGroup)
    }
}