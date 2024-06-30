package com.features.network_v2

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import com.features.network_v2.databinding.FNetworkV2Binding
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description : 네트워크 모듈 v2 리펙토링 관련 한 화면
 *
 * Created by juhongmin on 11/22/23
 */
@AndroidEntryPoint
internal class NetworkV2Fragment : BaseFragment<
        FNetworkV2Binding,
        NetworkV2FragmentViewModel>(
    R.layout.f_network_v2
) {

    override val viewModel: NetworkV2FragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            slider.valueTo = 50.0F
            slider.valueFrom = 2.0F
            slider.value = 2.0F
            startButton.setOnClickListener {
                showRefreshTokenTestPopup(slider.value.toInt())
            }
        }
    }

    private fun showRefreshTokenTestPopup(takeMinute: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage("${takeMinute}분 성능 테스트 진행 하시겠습니까?")
            .setCancelable(false)
            .setPositiveButton("확인") { _, _ -> viewModel.handleRefreshTokenTest(takeMinute) }
            .setNegativeButton("취소") { _, _ -> }
            .show()
    }
}
