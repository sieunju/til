package com.features.rv_simple_like.viewholders

import android.view.ViewGroup
import com.features.rv_simple_like.BR
import com.features.rv_simple_like.R
import com.features.rv_simple_like.databinding.VhSimpleLike1Binding
import com.hmju.core.ui.viewholders.BaseSimpleLikeViewHolder

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
class SimpleLike1ViewHolder(
    parent: ViewGroup
) : BaseSimpleLikeViewHolder<VhSimpleLike1Binding>(
    parent,
    R.layout.vh_simple_like_1
) {

    override fun onBindView(item: Any) {
        binding.setVariable(BR.model, item)
    }

    override fun onRefreshLike() {
        binding.invalidateAll()
    }
}