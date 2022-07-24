package com.features.recyclerview.ui.independent_viewholder

import android.view.ViewGroup
import com.features.recyclerview.R
import com.features.recyclerview.BR
import com.features.recyclerview.databinding.VhSimpleLikeRecyclerview3Binding

class SimpleLike3ViewHolder(
    parent: ViewGroup
) : BaseSimpleLikeViewHolder<VhSimpleLikeRecyclerview3Binding>(
    parent,
    R.layout.vh_simple_like_recyclerview_3
) {

    override fun onBindView(item: Any) {
        binding.setVariable(BR.item, item)
    }

    override fun onRefreshLike() {

    }
}