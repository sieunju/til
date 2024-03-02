package com.features.recyclerview.ui.independent_viewholder

import android.view.ViewGroup
import com.features.recyclerview.R
import com.features.recyclerview.BR
import com.features.recyclerview.databinding.VhSimpleLikeRecyclerview3Binding
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseSimpleLikeViewHolder

class SimpleLike3ViewHolder(
    parent: ViewGroup,
    viewModel: BaseViewModel? = null
) : BaseSimpleLikeViewHolder<VhSimpleLikeRecyclerview3Binding>(
    parent,
    R.layout.vh_simple_like_recyclerview_3
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