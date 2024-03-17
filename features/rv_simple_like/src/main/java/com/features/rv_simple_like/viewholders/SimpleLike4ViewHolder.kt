package com.features.rv_simple_like.viewholders

import android.view.ViewGroup
import com.features.rv_simple_like.BR
import com.features.rv_simple_like.R
import com.features.rv_simple_like.databinding.VhSimpleLike4Binding
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseViewHolder

/**
 * Description :
 *
 * Created by juhongmin on 3/2/24
 */
class SimpleLike4ViewHolder(
    parent: ViewGroup,
    viewModel: BaseViewModel? = null
) : BaseViewHolder<VhSimpleLike4Binding>(
    parent,
    R.layout.vh_simple_like_4
) {

    init {
        bindRequestManager(BR.requestManager, viewModel)
    }

    override fun onBindView(item: Any) {
        binding.setVariable(BR.model, item)
    }
}