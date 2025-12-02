package com.features.recyclerview

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import com.features.recyclerview.databinding.FRecyclerviewBinding
import com.features.rv_diff_util_2_bridge.RvDiffUtil2Bridge
import com.features.rv_diff_util_performance_bridge.RvDiffUtilPerformanceBridge
import com.features.rv_refactor_diff_util_bridge.RvRefactorDiffUtilBridge
import com.google.android.material.button.MaterialButton
import com.hmju.core.ui.base.BaseFragment
import com.hmju.core.ui.base.FragmentViewModel
import com.hmju.core_navigator.Route
import com.hmju.core_navigator.RouteParamsKey
import com.hmju.core_navigator.addQuery
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecyclerViewFragment : BaseFragment<FRecyclerviewBinding, FragmentViewModel>(
    R.layout.f_recyclerview
) {

    override val viewModel: FragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    @Inject
    lateinit var diffUtilPerformanceBridge: RvDiffUtilPerformanceBridge

    @Inject
    lateinit var rvRefactorDiffUtilBridge: RvRefactorDiffUtilBridge

    @Inject
    lateinit var rvDiffUtil2Bridge: RvDiffUtil2Bridge

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
	super.onViewCreated(view, savedInstanceState)
	initButton()
    }

    private fun initButton() {
	binding.llButtons.runCatching {
	    addButton("컨트롤러와 뷰홀더 간의 의존성 제거 해보기") {
		viewModel.sendNavigate(Route.RECYCLERVIEW_SIMPLE_LIKE.getUri {
		    addQuery(RouteParamsKey.LAYOUT_ID, R.id.fragment)
		    addQuery(RouteParamsKey.IS_INTERNAL, true)
		})
	    }
	    addButton("AAC 페이징 라이브러리 안쓰고 페이징 구현해보기") {
		viewModel.sendNavigate(Route.RECYCLERVIEW_PAGING.getUri {
		    addQuery(RouteParamsKey.LAYOUT_ID, R.id.fragment)
		    addQuery(RouteParamsKey.IS_INTERNAL, true)
		})
	    }
	    addButton("DiffUtil 퍼포먼스 테스트") {
		diffUtilPerformanceBridge.moveToPage(R.id.fragment, parentFragmentManager)
	    }
	    addButton("유지보수하기 쉽게 DiffUtil 사용해보기") {
		rvRefactorDiffUtilBridge.moveToPage(R.id.fragment, parentFragmentManager)
	    }
	    addButton("DiffUtil 고질적인 문제 처리해보기 v1") {
		rvDiffUtil2Bridge.moveToPage(R.id.fragment, parentFragmentManager)
	    }
	}
    }

    private fun LinearLayoutCompat.addButton(title: String, onClick: () -> Unit) {
	addView(MaterialButton(requireContext()).apply {
	    text = title
	    setOnClickListener { onClick() }
	})
    }
}
