package com.features.network_v2

import com.features.network_v2.databinding.FNetworkV2Binding
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description : 네트워크 모듈 v2 리펙토링 관련 한 화면
 *
 * Created by juhongmin on 11/22/23
 */
@AndroidEntryPoint
class NetworkV2Fragment : BaseFragment<
        FNetworkV2Binding,
        NetworkV2FragmentViewModel>(
    R.layout.f_network_v2
) {

    override val viewModel: NetworkV2FragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm
}
