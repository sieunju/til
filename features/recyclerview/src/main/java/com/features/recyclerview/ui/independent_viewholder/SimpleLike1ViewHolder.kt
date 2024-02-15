package com.features.recyclerview.ui.independent_viewholder

import android.view.ViewGroup
import com.features.recyclerview.BR
import com.features.recyclerview.R
import com.features.recyclerview.databinding.VhSimpleLikeRecyclerview1Binding
import com.features.recyclerview.models.GoodsOneUiModel
import com.features.recyclerview.models.GoodsTwoUiModel


/**
 * Description : 간단한 좋아요 아이템 샘플 1
 *
 * Created by juhongmin on 2022/01/15
 */
class SimpleLike1ViewHolder(
    parent: ViewGroup
) : BaseSimpleLikeViewHolder<VhSimpleLikeRecyclerview1Binding>(
    parent,
    R.layout.vh_simple_like_recyclerview_1
) {

    init {
        binding.imgLike.setOnClickListener {
            simpleLikeClick(it, binding.model)
        }
    }

    override fun onBindView(item: Any) {
        if (item is GoodsOneUiModel) {
            binding.setVariable(BR.model, item.model)
        } else if (item is GoodsTwoUiModel) {
            binding.setVariable(BR.model, item.model)
        }
    }

    override fun onRefreshLike() {
        simpleLikeChange(binding.imgLike, binding.model)
    }
}
