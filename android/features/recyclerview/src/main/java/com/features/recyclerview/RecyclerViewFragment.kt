package com.features.recyclerview

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.features.core_ui.base.BaseFragment
import com.features.core_ui.base.FragmentViewModel
import com.features.recyclerview.databinding.FRecyclerviewBinding
import com.features.recyclerview.ui.custom_paging.CustomPagingFragment
import com.features.recyclerview.ui.diffutil_performance.DiffUtilPerformanceFragment
import com.features.recyclerview.ui.diffutil_refactor.RefactorDiffUtilFragment
import com.features.recyclerview.ui.diffutil_v2.DiffUtil2Fragment
import com.features.recyclerview.ui.independent_viewholder.SimpleLikeRecyclerViewFragment
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecyclerViewFragment :
    BaseFragment<FRecyclerviewBinding, FragmentViewModel>(R.layout.f_recyclerview) {

    override val viewModel: FragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.llButtons.runCatching {
            addButton("컨트롤러와 뷰홀더 간의 의존성 제거 해보기", SimpleLikeRecyclerViewFragment())
            addButton("AAC 페이징 라이브러리 안쓰고 페이징 구현해보기", CustomPagingFragment())
            addButton("DiffUtil 퍼포먼스 테스트",DiffUtilPerformanceFragment())
            addButton("유지보수하기 쉽게 DiffUtil 사용해보기", RefactorDiffUtilFragment())
            addButton("DiffUtil 고질적인 문제 처리해보기 v1", DiffUtil2Fragment())
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
