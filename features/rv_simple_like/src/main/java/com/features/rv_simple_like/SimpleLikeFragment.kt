package com.features.rv_simple_like

import com.features.rv_simple_like.databinding.FSimpleLikeBinding
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description : 뷰모델과 뷰홀더간의 의존성을 떼어내기 위한 학습? 공부? 고민?
 *
 * Created by juhongmin on 3/2/24
 */
@AndroidEntryPoint
class SimpleLikeFragment : BaseFragment<FSimpleLikeBinding, SimpleLikeFragmentViewModel>(
    R.layout.f_simple_like
) {
    override val viewModel: SimpleLikeFragmentViewModel by initViewModel()
    override val bindingVariable: Int get() = BR.vm
}
