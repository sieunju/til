package com.hmju.core.ui.base

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.viewmodel.CreationExtras
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
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

        dialog?.setOnShowListener { onShow(it) }
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

    /**
     * BottomSheet Show 상태시 호출되는 함수
     */
    @CallSuper
    open fun onShow(dialogInterface: DialogInterface) {
    }

    /**
     * Tag 값은 리터널 상수로 처리해야 합니다.
     * @param tag Tag
     */
    open fun simpleShow(fm: FragmentManager, tag: String) {
        runCatching {
            // 이미 보여지고 있는 Dialog 인경우 스킵
            if (!isAdded) {
                super.show(fm, tag)
            }
        }
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

    /**
     * BottomSheet 전체 화면 처리하는 함수
     *
     * [setFullHeightBottomSheet] 함수를 사용하면 됩니다.
     */
    protected fun setFullHeightBottomSheet(
        dialogInterface: DialogInterface
    ): BottomSheetBehavior<View>? {
        return try {
            val bottomSheet = dialogInterface as BottomSheetDialog
            val view = bottomSheet.findViewById<View>(
                com.google.android.material.R.id.design_bottom_sheet
            ) as View
            view.updateLayoutParams<ViewGroup.LayoutParams> {
                height = ViewGroup.LayoutParams.MATCH_PARENT
            }
            BottomSheetBehavior.from(view)
        } catch (ex: Exception) {
            null
        }
    }
}
