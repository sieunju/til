package com.features.recyclerview.ui.independent_viewholder

import android.view.ViewGroup
import com.features.recyclerview.BR
import com.features.recyclerview.R
import com.features.recyclerview.databinding.VhSimpleLikeRecyclerview2Binding
import com.features.recyclerview.models.GoodsTwoUiModel
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseSimpleLikeViewHolder

/**
 * Description : 간단한 좋아요 아이템 샘플 1
 *
 * Created by juhongmin on 2022/01/15
 */
class SimpleLike2ViewHolder(
    parent: ViewGroup,
    viewModel: BaseViewModel? = null
) : BaseSimpleLikeViewHolder<VhSimpleLikeRecyclerview2Binding>(
    parent,
    R.layout.vh_simple_like_recyclerview_2
) {

    init {
        bindRequestManager(BR.requestManager, viewModel)
        binding.imgLike.setOnClickListener {
            val model = binding.model ?: return@setOnClickListener
            simpleLikeClick(it, model.id)
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
        val model = binding.model ?: return
        simpleLikeChange(binding.imgLike, model.id)
    }
}
