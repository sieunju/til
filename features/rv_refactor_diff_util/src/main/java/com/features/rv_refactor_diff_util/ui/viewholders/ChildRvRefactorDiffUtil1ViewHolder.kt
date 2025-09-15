package com.features.rv_refactor_diff_util.ui.viewholders

import android.view.ViewGroup
import com.features.rv_refactor_diff_util.BR
import com.features.rv_refactor_diff_util.R
import com.features.rv_refactor_diff_util.databinding.VhRvRefactorDiffUtil1Binding
import com.hmju.core.ui.base.BaseViewModel
import com.hmju.core.ui.viewholders.BaseViewHolder

/**
 * Description :
 *
 * Created by juhongmin on 3/8/24
 */
class ChildRvRefactorDiffUtil1ViewHolder(
    parent: ViewGroup,
    viewModel: BaseViewModel? = null
) : BaseViewHolder<VhRvRefactorDiffUtil1Binding>(
    parent,
    R.layout.vh_rv_refactor_diff_util_1
) {
    init {
        bindRequestManager(BR.requestManager, viewModel)
    }

    override fun onBindView(item: Any) {
        binding.setVariable(BR.model, item)
    }
}