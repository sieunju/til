package com.features.recyclerview.ui.independent_viewholder

import android.view.ViewGroup
import com.features.recyclerview.BR
import com.features.recyclerview.R
import com.features.recyclerview.databinding.VhSimpleLikeRecyclerview2Binding
import com.features.recyclerview.model.GoodsTwoUiModel

/**
 * Description : 간단한 좋아요 아이템 샘플 1
 *
 * Created by juhongmin on 2022/01/15
 */
class SimpleLike2ViewHolder(
    parent: ViewGroup
) : BaseSimpleLikeViewHolder<VhSimpleLikeRecyclerview2Binding>(
    parent,
    R.layout.vh_simple_like_recyclerview_2
) {

    init {
        binding.imgLike.setOnClickListener {
            simpleLikeClick(it, binding.item)
        }
    }

    override fun onBindView(item: Any) {
        if (item is GoodsTwoUiModel) {
            binding.setVariable(BR.item, item.item)
        } else {
            binding.setVariable(BR.item, item)
        }
    }

    override fun onRefreshLike() {
        simpleLikeChange(binding.imgLike, binding.item)
    }
}