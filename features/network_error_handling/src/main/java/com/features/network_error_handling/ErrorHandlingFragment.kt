package com.features.network_error_handling

import com.features.network_error_handling.databinding.FErrorHandlingBinding
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description : HTTP API 에러 핸들링 Fragment
 *
 * Created by juhongmin on 2022/05/12
 */
@AndroidEntryPoint
class ErrorHandlingFragment : BaseFragment<FErrorHandlingBinding, ErrorHandlingViewModel>(
    R.layout.f_error_handling
) {
    override val viewModel: ErrorHandlingViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm
}
