package com.features.rv_custom_paging.ui

import com.features.rv_custom_paging.BR
import com.features.rv_custom_paging.R
import com.features.rv_custom_paging.databinding.FCustomPagingBinding
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description :
 *
 * Created by juhongmin on 2/15/24
 */
@AndroidEntryPoint
class CustomPagingFragment : BaseFragment<FCustomPagingBinding, CustomPagingFragmentViewModel>(
    R.layout.f_custom_paging
) {
    override val viewModel: CustomPagingFragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm
}
