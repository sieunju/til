package com.features.rv_diff_util_2.ui

import android.os.Bundle
import android.view.View
import com.features.rv_diff_util_2.BR
import com.features.rv_diff_util_2.R
import com.features.rv_diff_util_2.databinding.FDiffUtil2Binding
import com.hmju.core.ui.adapter.ItemListAdapterV2
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description :
 *
 * Created by juhongmin on 3/10/24
 */
@AndroidEntryPoint
class DiffUtil2Fragment : BaseFragment<FDiffUtil2Binding, DiffUtil2ViewModel>(
    R.layout.f_diff_util_2
) {
    override val viewModel: DiffUtil2ViewModel by initViewModel()
    override val bindingVariable: Int get() = BR.vm

    private val adapter: ItemListAdapterV2 by lazy { ItemListAdapterV2() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.setViewModel(viewModel)
        binding.rvContents.adapter = adapter
        with(viewModel) {
            dataList.observe(viewLifecycleOwner) { adapter.submitList(it) }
        }
    }
}
