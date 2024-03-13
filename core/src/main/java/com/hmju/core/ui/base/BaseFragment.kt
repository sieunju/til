package com.hmju.core.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

/**
 * Description : MVVM BaseFragment
 *
 * Created by juhongmin on 2022/03/19
 */
abstract class BaseFragment<T : ViewDataBinding, VM : FragmentViewModel>(
    @LayoutRes private val layoutId: Int
) : Fragment() {

    abstract val viewModel: VM
    abstract val bindingVariable: Int // ViewModel Binding Variable
    private var _binding: T? = null
    val binding: T get() = _binding!!

    private var isInit = false

    private val fragmentResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val reqCode = result.data?.extras?.getInt(BaseActivity.REQ_CODE) ?: -1
        viewModel.onFragmentResult(reqCode, result.resultCode, result.data?.extras ?: Bundle())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initBinding(inflater, container)
    }

    /**
     * initBinding
     */
    private fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        return DataBindingUtil.inflate<T>(inflater, layoutId, container, false).run {
            _binding = this
            lifecycleOwner = viewLifecycleOwner
            setVariable(bindingVariable, viewModel)
            viewModel.initRequestManager(Glide.with(this@BaseFragment))
            this.root
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            setLifecycle(Lifecycle.Event.ON_CREATE)
            onDirectViewCreated()
            startActivityPage.observe(viewLifecycleOwner) { startActivityAndAnimation(it) }
            startFinishEvent.observe(viewLifecycleOwner) { requireActivity().finish() }
        }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        with(viewModel) {
            setLifecycle(Lifecycle.Event.ON_RESUME)
            onDirectCreatedToResumed()
            if (isInit) {
                onDirectResumed()
            }
            isInit = true
        }
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        with(viewModel) {
            setLifecycle(Lifecycle.Event.ON_STOP)
            onDirectStop()
        }
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        isInit = false
        with(viewModel) {
            setLifecycle(Lifecycle.Event.ON_DESTROY)
            clearDisposable()
            clearRequestManager()
        }
        _binding = null
    }

    @CallSuper
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            viewModel.onDirectShown()
        }
    }

    /**
     * 기본 viewModels 와 같은 로직의 함수
     */
    protected inline fun <reified VM : FragmentViewModel> initViewModel(): Lazy<VM> {
        return createViewModelLazy(VM::class, { viewModelStore })
    }

    /**
     * Parent Fragment ViewModel 공유하기위한 함수
     * Lazy 로 선언한하고 직접적으로 가져올때 사용하는 함수
     */
    protected inline fun <reified VM : BaseViewModel> parentViewModel(parentFragment: Fragment): VM {
        return ViewModelProvider(
            parentFragment.viewModelStore,
            parentFragment.defaultViewModelProviderFactory
        )[VM::class.java]
    }

//    /**
//     * onActivityResult 에 대한 처리
//     * ReactiveX 타입
//     * @param reqCode RequestCode
//     * @param resCode Result Code
//     * @param data 전달 받을 데이터
//     */
//    private fun handleFragmentResult(reqCode: Int, resCode: Int, data: Bundle?): Disposable {
//        return Flowable.fromIterable(javaClass.methods.toList())
//            .filter { it.isAnnotationPresent(OnFragmentResult::class.java) }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnNext { method ->
//                method.getAnnotation(OnFragmentResult::class.java)?.let { annotation ->
//                    // RequestCode, RESULT Code 와 같은 함수만 호출
//                    if (annotation.requestCode == reqCode && annotation.resCode == resCode) {
//                        method.invoke(this, data)
//                    }
//                }
//            }.subscribe()
//    }

    /**
     * ActivityResult to Intent 변환 처리함수
     */
    private fun getActivityResultIntent(page: ActivityResult): Intent {
        return Intent(requireContext(), page.targetActivity.java).apply {
            if (page.flags != -1) {
                flags = page.flags
            }
            page.data.putInt(BaseActivity.REQ_CODE, page.requestCode)
            putExtras(page.data)
        }
    }

    /**
     * Start Activity And Animation
     */
    private fun startActivityAndAnimation(
        result: ActivityResult
    ) {
        val intent = getActivityResultIntent(result)
        if (result.requestCode != -1) {
            val options = if (result.isValidateAni()) {
                ActivityOptionsCompat.makeCustomAnimation(
                    requireContext(),
                    result.enterAni,
                    result.exitAni
                )
            } else {
                null
            }
            fragmentResult.launch(intent, options)
        } else {
            startActivity(intent)
            if (result.isValidateAni()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    activity?.overrideActivityTransition(
                        Activity.OVERRIDE_TRANSITION_OPEN,
                        result.enterAni,
                        result.exitAni
                    )
                } else {
                    @Suppress("DEPRECATION")
                    activity?.overridePendingTransition(result.enterAni, result.exitAni)
                }
            }
        }
    }
}
