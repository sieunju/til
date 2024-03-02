package com.features.recyclerview.ui.independent_viewholder

import android.view.ViewGroup
import com.features.recyclerview.BR
import com.features.recyclerview.R
import com.features.recyclerview.databinding.VhSimpleLikeRecyclerview4Binding
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseSimpleLikeViewHolder

class SimpleLike4ViewHolder(
    parent: ViewGroup,
    viewModel: BaseViewModel? = null
) : BaseSimpleLikeViewHolder<VhSimpleLikeRecyclerview4Binding>(
    parent,
    R.layout.vh_simple_like_recyclerview_4
) {

    init {
        bindRequestManager(BR.requestManager, viewModel)
    }

    override fun onBindView(item: Any) {
        binding.setVariable(BR.model, item)
    }

    override fun onRefreshLike() {

    }
}