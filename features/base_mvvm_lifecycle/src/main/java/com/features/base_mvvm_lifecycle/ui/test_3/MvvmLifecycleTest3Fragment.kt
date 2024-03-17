package com.features.base_mvvm_lifecycle.ui.test_3

import android.os.Bundle
import android.view.View
import com.features.base_mvvm_lifecycle.BR
import com.features.base_mvvm_lifecycle.R
import com.features.base_mvvm_lifecycle.databinding.FMvvmLifecycle3Binding
import com.features.base_mvvm_lifecycle.ui.test_4.MvvmLifecycleTest4Fragment
import com.google.android.material.snackbar.Snackbar
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Description :
 *
 * Created by juhongmin on 2022/03/17
 */
@AndroidEntryPoint
class MvvmLifecycleTest3Fragment :
    BaseFragment<FMvvmLifecycle3Binding, MvvmLifecycleTest3FragmentViewModel>(
        R.layout.f_mvvm_lifecycle_3
    ) {
    override val viewModel: MvvmLifecycleTest3FragmentViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            startFragment.observe(viewLifecycleOwner) {
                parentFragmentManager.beginTransaction().apply {
                    add(R.id.container, MvvmLifecycleTest4Fragment())
                    addToBackStack("MvvmLifecycleTest4Fragment")
                    commit()
                }
            }
            startToast.observe(viewLifecycleOwner) {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}