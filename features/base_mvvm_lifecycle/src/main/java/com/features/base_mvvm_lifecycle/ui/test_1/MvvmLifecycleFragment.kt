package com.features.base_mvvm_lifecycle.ui.test_1

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.features.base_mvvm_lifecycle.BR
import com.features.base_mvvm_lifecycle.R
import com.features.base_mvvm_lifecycle.databinding.FMvvmLifecycleBinding
import com.hmju.core.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/26
 */
@AndroidEntryPoint
class MvvmLifecycleFragment : BaseFragment<FMvvmLifecycleBinding, MvvmLifecycleViewModel>(
    R.layout.f_mvvm_lifecycle
) {

    override val viewModel: MvvmLifecycleViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    private lateinit var callback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (childFragmentManager.backStackEntryCount > 0) {
                    childFragmentManager.popBackStack()
                } else {
                    parentFragmentManager.popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            activityResult.observe(viewLifecycleOwner) {
                // onResume 일때만
                Timber.d("RESRSE $it")
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}
