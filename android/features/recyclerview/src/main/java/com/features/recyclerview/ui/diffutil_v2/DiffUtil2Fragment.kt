package com.features.recyclerview.ui.diffutil_v2

import android.os.Bundle
import android.view.View
import com.features.recyclerview.BR
import com.features.recyclerview.R
import com.features.recyclerview.adapter.ItemListAdapter
import com.features.recyclerview.databinding.FDiffUtil2Binding
import com.hmju.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiffUtil2Fragment :
    BaseFragment<FDiffUtil2Binding, DiffUtil2ViewModel>(R.layout.f_diff_util2) {

    override val viewModel: DiffUtil2ViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private val adapter : ItemListAdapter by lazy { ItemListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvContents.adapter = adapter
        with(viewModel) {
            dataList.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        }
    }
}
