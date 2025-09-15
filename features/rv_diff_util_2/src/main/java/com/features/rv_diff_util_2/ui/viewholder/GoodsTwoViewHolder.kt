package com.features.rv_diff_util_2.ui.viewholder

import android.view.ViewGroup
import com.features.rv_diff_util_2.BR
import com.features.rv_diff_util_2.R
import com.features.rv_diff_util_2.databinding.VhRvDiffUtil2Binding
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseViewHolder

/**
 * Description :
 *
 * Created by juhongmin on 3/10/24
 */
class GoodsTwoViewHolder(
    parent: ViewGroup,
    viewModel: BaseViewModel? = null
) : BaseViewHolder<VhRvDiffUtil2Binding>(
    parent,
    R.layout.vh_rv_diff_util_2
) {

    init {
        bindRequestManager(BR.requestManager, viewModel)
    }

    override fun onBindView(item: Any) {
        binding.setVariable(BR.model, item)
    }
}