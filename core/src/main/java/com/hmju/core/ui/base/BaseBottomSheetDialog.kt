package com.hmju.core.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.CreationExtras
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber

/**
 * Description : BaseBottomSheetDialog
 *
 * Created by juhongmin on 2022/04/15
 */
abstract class BaseBottomSheetDialog<T : ViewDataBinding, VM : BottomSheetViewModel>(
    @LayoutRes private val layoutId: Int
) : BottomSheetDialogFragment() {

    lateinit var viewModel: VM
    abstract val bindingVariable: Int // ViewModel Binding Variable
    private var _binding: T? = null
    val binding: T get() = _binding!!

    private var isInit = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<T>(inflater, layoutId, container, false).run {
            _binding = this
            lifecycleOwner = viewLifecycleOwner
            setVariable(bindingVariable, viewModel)
            this.root
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.runCatching { onDirectViewCreated() }

        with(viewModel) {
            startActivityPage.observe(viewLifecycleOwner) {
                Intent(requireContext(), it.targetActivity.java).apply {
                    if (it.flags != -1) {
                        flags = it.flags
                    }
                    putExtras(it.data)

                    startActivity(this)
                }
            }
        }

        dialog?.setOnDismissListener {
            dismiss()
        }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        viewModel.runCatching {
            onDirectCreatedToResumed()

            if (isInit) {
                onDirectResumed()
            }
        }
        isInit = true
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        viewModel.runCatching { onDirectStop() }
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearDisposable()
        _binding = null
    }

    @CallSuper
    override fun dismiss() {
        super.dismiss()
        Timber.d("${javaClass.simpleName} Dismiss")
    }

    fun simpleShow(fm: FragmentManager) {
        super.show(fm, javaClass.simpleName)
    }

    /**
     * 기본 viewModels 와 같은 로직의 함수
     */
    protected inline fun <reified VM : BottomSheetViewModel> initViewModel(
        noinline extrasProducer: (() -> CreationExtras)? = null
    ): VM {
        return ViewModelProvider(
            viewModelStore,
            defaultViewModelProviderFactory,
            extrasProducer?.invoke() ?: this.defaultViewModelCreationExtras
        ).get()
    }
}
