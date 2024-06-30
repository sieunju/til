package com.features.rv_custom_paging.viewholder

import android.view.ViewGroup
import com.features.rv_custom_paging.BR
import com.features.rv_custom_paging.R
import com.features.rv_custom_paging.databinding.VhGoodsBinding
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseViewHolder

internal class GoodsViewHolder(
    parent: ViewGroup,
    viewModel: BaseViewModel? = null
) : BaseViewHolder<VhGoodsBinding>(parent, R.layout.vh_goods) {

    init {
        bindRequestManager(BR.requestManager, viewModel)
    }

    override fun onBindView(item: Any) {
        binding.setVariable(BR.model, item)
    }
}
