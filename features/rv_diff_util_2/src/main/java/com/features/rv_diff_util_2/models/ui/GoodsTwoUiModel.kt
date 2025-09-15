package com.features.rv_diff_util_2.models.ui

import com.hmju.core.ui.base.BaseUiModel
import com.features.rv_diff_util_2.R
import com.features.rv_diff_util_2.ui.viewholder.GoodsTwoViewHolder
import com.hmju.core.ui.viewholders.BaseViewHolder
import kotlin.reflect.KClass

/**
 * Description :
 *
 * Created by juhongmin on 3/10/24
 */
data class GoodsTwoUiModel(
    val model: GoodsModel
) : BaseUiModel(R.layout.vh_rv_diff_util_2){
    override fun getClassName(): String {
        return "GoodsTwoUiModel"
    }

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
        return GoodsTwoViewHolder::class
    }

    val title: String get() = model.title
    val message: String get() = model.message
    val imagePath: String get() = model.imagePath
}
