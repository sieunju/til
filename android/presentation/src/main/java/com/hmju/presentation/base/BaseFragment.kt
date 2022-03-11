package com.hmju.presentation.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.hmju.lifecycle.OnCreated
import com.hmju.lifecycle.OnResumed
import com.hmju.lifecycle.OnStopped
import com.hmju.lifecycle.OnViewCreated
import com.hmju.presentation.BR
import com.hmju.presentation.lifecycle.LifecycleController
import timber.log.Timber

/**
 * Description : BaseFragment Class
 *
 * Created by juhongmin on 2022/02/26
 */
abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    abstract val viewModel: VM
    abstract val binding: B

    private var isInit = false

    protected fun lifecycle(): LifecycleController = viewModel.lifecycleController

    private val activityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Timber.d("Activity Result $it")
        }

    private val permissionResult =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            Timber.d("Permission Result $it")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("${javaClass.simpleName} onCreate $isInit")
        lifecycle().onInit()
        viewModel.performLifecycle<OnCreated>()
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@BaseFragment
            setVariable(BR.vm, viewModel)
        }
        performLiveData()
        viewModel.performLifecycle<OnViewCreated>()
    }

    /**
     * BaseFragment 에서 공통으로 처리할 LiveData 을 셋팅하는 함수
     */
    private fun performLiveData() {
        with(viewModel) {
            startActivity.observe(viewLifecycleOwner) { entity ->
                Timber.d("Activity $entity")
                Intent(requireContext(), entity.target).apply {
                    entity.flags?.let { flags = it }
                    entity.bundle?.let { putExtras(it) }
                    requireContext().startActivity(this)
                }
            }

            startActivityResult.observe(viewLifecycleOwner) { entity ->
                Timber.d("ActivityResult $entity")
                entity.requestCode?.let { code ->
                    activityResult.launch(
                        Intent(
                            requireContext(),
                            entity.target
                        ).apply {
                            entity.flags?.let { flags = it }
                            entity.bundle?.let { putExtras(it) }
                            putExtra(BaseViewModel.REQ_CODE, code)
                        })
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("${javaClass.simpleName} onResume $isInit")
        if (isInit) {
            lifecycle().onVisible()
            viewModel.performLifecycle<OnResumed>()
        }
        isInit = true
    }

    override fun onStop() {
        super.onStop()
        Timber.d("${javaClass.simpleName} onStop")
        lifecycle().onInVisible()
        viewModel.performLifecycle<OnStopped>()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("${javaClass.simpleName} onDestroyView $isInit")
        isInit = false
        viewModel.clearDisposable()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("${javaClass.simpleName} onDestroy")
        lifecycle().onRelease()
    }
}
