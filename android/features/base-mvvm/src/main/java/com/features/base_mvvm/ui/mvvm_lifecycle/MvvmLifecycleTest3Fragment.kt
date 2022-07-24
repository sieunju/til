package com.features.base_mvvm.ui.mvvm_lifecycle

import android.os.Bundle
import android.view.View
import com.features.base_mvvm.BR
import com.features.base_mvvm.R
import com.features.base_mvvm.databinding.FMvvmLifecycle3Binding
import com.google.android.material.snackbar.Snackbar
import com.hmju.core.BaseFragment
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
                    addToBackStack("")
                    commit()
                }
            }
            startToast.observe(viewLifecycleOwner) {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}