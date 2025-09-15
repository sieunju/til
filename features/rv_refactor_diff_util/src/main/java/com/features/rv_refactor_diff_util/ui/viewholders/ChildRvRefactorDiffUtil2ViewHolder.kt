package com.features.rv_refactor_diff_util.ui.viewholders

import android.view.ViewGroup
import com.features.rv_refactor_diff_util.BR
import com.features.rv_refactor_diff_util.R
import com.features.rv_refactor_diff_util.databinding.VhRvRefactorDiffUtil2Binding
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseViewHolder

/**
 * Description :
 *
 * Created by juhongmin on 3/9/24
 */
class ChildRvRefactorDiffUtil2ViewHolder(
    parent: ViewGroup,
    viewModel: BaseViewModel? = null
) : BaseViewHolder<VhRvRefactorDiffUtil2Binding>(
    parent,
    R.layout.vh_rv_refactor_diff_util_2
) {

    init {
        bindRequestManager(BR.requestManager, viewModel)
    }

    override fun onBindView(item: Any) {
        binding.setVariable(BR.model, item)
    }
}
