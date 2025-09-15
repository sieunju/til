package com.features.base_mvvm_lifecycle.ui.test_4

import android.os.Bundle
import android.view.View
import com.features.base_mvvm_lifecycle.R
import com.features.base_mvvm_lifecycle.BR
import com.features.base_mvvm_lifecycle.databinding.FMvvmLifecycle4Binding
import com.features.base_mvvm_lifecycle.ui.test_3.MvvmLifecycleTest3Fragment
import com.hmju.core.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description :
 *
 * Created by juhongmin on 2022/03/17
 */
@AndroidEntryPoint
class MvvmLifecycleTest4Fragment :
    BaseFragment<FMvvmLifecycle4Binding, MvvmLifecycleTest4FragmentViewModel>(
        R.layout.f_mvvm_lifecycle_4
    ) {
    override val viewModel: MvvmLifecycleTest4FragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            startFragment.observe(viewLifecycleOwner) {
                parentFragmentManager.beginTransaction().apply {
                    add(R.id.container, MvvmLifecycleTest3Fragment())
                    addToBackStack(null)
                    commit()
                }
            }
            startToast.observe(viewLifecycleOwner) {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}