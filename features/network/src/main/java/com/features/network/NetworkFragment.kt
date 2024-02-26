package com.features.network

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.features.network.databinding.FNetworkBinding
import com.features.network.ui.expired_token.RefreshTokenFragment
import com.features.network.ui.json_jsend.JsonJsendFragment
import com.features.network_error_handling_bridge.NetworkErrorHandlingBridge
import com.features.network_v2_bridge.NetworkV2Bridge
import com.google.android.material.button.MaterialButton
import com.hmju.core.ui.base.BaseFragment
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NetworkFragment : BaseFragment<FNetworkBinding, FragmentViewModel>(R.layout.f_network) {
    override val viewModel: FragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    @Inject
    lateinit var v2Bridge: NetworkV2Bridge

    @Inject
    lateinit var errorHandlingBridge: NetworkErrorHandlingBridge

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.llButtons.runCatching {
            addButton("토큰 만료시 재인증하는 방법") {
                moveToFragment(RefreshTokenFragment())
            }
            addButton("JSON jsend 규칙으로 데이터 모델 구성해보기") {
                moveToFragment(JsonJsendFragment())
            }
            addButton("네트워크 에러 헨들링 처리해보기") {
                errorHandlingBridge.moveToPage(R.id.fragment, parentFragmentManager)
            }
            addButton("네트워크 V2 리펙토링") {
                v2Bridge.moveToPage(R.id.fragment, parentFragmentManager)
            }
        }
    }

    private fun LinearLayoutCompat.addButton(title: String, callback: () -> Unit) {
        addView(MaterialButton(requireContext()).apply {
            text = title
            setOnClickListener { callback() }
        })
    }

    private fun moveToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment)
            addToBackStack(null)
            commit()
        }
    }
}
