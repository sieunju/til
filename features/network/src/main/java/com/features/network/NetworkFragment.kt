package com.features.network

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import com.features.network.databinding.FNetworkBinding
import com.features.network_jsend_format_bridge.NetworkJSendFormatBridge
import com.features.network_v2_bridge.NetworkV2Bridge
import com.google.android.material.button.MaterialButton
import com.hmju.core.ui.base.BaseFragment
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.RouteParamsKey
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NetworkFragment : BaseFragment<FNetworkBinding, FragmentViewModel>(
    R.layout.f_network
) {
    override val viewModel: FragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    @Inject
    lateinit var v2Bridge: NetworkV2Bridge

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
	super.onViewCreated(view, savedInstanceState)
	initButton()
    }

    private fun initButton() {
	binding.llButtons.runCatching {
	    addButton("토큰 만료시 재인증하는 방법") {
		viewModel.sendNavigate(Route.NETWORK_EXPIRED_TOKEN.getUri {
		    appendQueryParameter(RouteParamsKey.LAYOUT_ID, R.id.fragment.toString())
		    appendQueryParameter(RouteParamsKey.IS_INTERNAL, true.toString())
		})
	    }
	    addButton("JSON jsend 규칙으로 데이터 모델 구성해보기") {
		viewModel.sendNavigate(Route.NETWORK_JSEND_FORMAT.getUri {
		    appendQueryParameter(RouteParamsKey.LAYOUT_ID, R.id.fragment.toString())
		    appendQueryParameter(RouteParamsKey.IS_INTERNAL, true.toString())
		})
	    }
	    addButton("네트워크 에러 헨들링 처리해보기") {
		viewModel.sendNavigate(Route.NETWORK_ERROR_HANDLING.getUri {
		    appendQueryParameter(RouteParamsKey.LAYOUT_ID, R.id.fragment.toString())
		    appendQueryParameter(RouteParamsKey.IS_INTERNAL, true.toString())
		})
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
}
