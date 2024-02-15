package com.features.recyclerview.ui.independent_viewholder

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.features.recyclerview.BR
import com.features.recyclerview.R
import com.features.recyclerview.databinding.VhSimpleLikeRecyclerview2Binding
import com.features.recyclerview.models.GoodsTwoUiModel

/**
 * Description : 간단한 좋아요 아이템 샘플 1
 *
 * Created by juhongmin on 2022/01/15
 */
class SimpleLike2ViewHolder(
    parent: ViewGroup,
    viewModel: ViewModel? = null
) : BaseSimpleLikeViewHolder<VhSimpleLikeRecyclerview2Binding>(
    parent,
    R.layout.vh_simple_like_recyclerview_2
) {

    init {
        binding.imgLike.setOnClickListener {
            simpleLikeClick(it, binding.model)
        }
    }

    override fun onBindView(item: Any) {
        if (item is GoodsTwoUiModel) {
            binding.setVariable(BR.model, item.model)
        } else {
            binding.setVariable(BR.model, item)
        }
    }

    override fun onRefreshLike() {
        simpleLikeChange(binding.imgLike, binding.model)
    }
}
