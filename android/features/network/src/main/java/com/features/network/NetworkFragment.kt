package com.features.network

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.features.core_ui.base.BaseFragment
import com.features.core_ui.base.FragmentViewModel
import com.features.network.databinding.FNetworkBinding
import com.features.network.ui.error_handling.ErrorHandlingFragment
import com.features.network.ui.expired_token.RefreshTokenFragment
import com.features.network.ui.json_jsend.JsonJsendFragment
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NetworkFragment : BaseFragment<FNetworkBinding, FragmentViewModel>(R.layout.f_network) {
    override val viewModel: FragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.llButtons.runCatching {
            addButton("토큰 만료시 재인증하는 방법", RefreshTokenFragment())
            addButton("JSON jsend 규칙으로 데이터 모델 구성해보기", JsonJsendFragment())
            addButton("네트워크 에러 헨들링 처리해보기", ErrorHandlingFragment())
        }
    }

    private fun LinearLayoutCompat.addButton(title: String, targetFragment: Fragment) {
        addView(MaterialButton(requireContext()).apply {
            text = title
            setOnClickListener {
                moveToFragment(targetFragment)
            }
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
