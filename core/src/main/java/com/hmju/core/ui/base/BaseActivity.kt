package com.hmju.core.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import timber.log.Timber

/**
 * Description : MVVM BaseActivity
 *
 * Created by juhongmin on 2022/03/19
 */
abstract class BaseActivity<T : ViewDataBinding, VM : ActivityViewModel>(
    @LayoutRes private val layoutId: Int
) : AppCompatActivity() {

    companion object {
        const val REQ_CODE = "req_code"
        const val RES_CODE = "res_code"
    }

    abstract val viewModel: VM
    abstract val bindingVariable: Int // ViewModel Binding Variable
    lateinit var binding: T

    private var isInit = false

    private val activityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            viewModel.runCatching {
                val reqCode = it.data?.extras?.getInt(REQ_CODE) ?: -1
                Timber.d("Activity ResultCode ${it.resultCode} ReqCode $reqCode ${it.data?.extras}")
                addDisposable(handleActivityResult(reqCode, it.resultCode, it.data?.extras))
            }
        }

    private val permissionResult =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            Timber.d("Permission Result $it")
            runCatching {
                viewModel.performPermissionResult(it)
            }
        }

    private var permissionDisposable: Disposable? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBinding()

        viewModel.runCatching {
            onDirectCreate()
            onIntent()
        }

        with(viewModel) {
            startActivityPage.observe(this@BaseActivity) { startActivityAndAnimation(it) }
        }
    }

    @CallSuper
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModel.runCatching {
            val data = intent?.extras
            if (data != null) {
                data.keySet()?.forEach { key ->
                    savedStateHandle.set(key, data.get(key))
                }
                onIntent()
            }
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

        // StartActivityResult observer
        performPermissions()
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        viewModel.runCatching { onDirectStop() }

        // ActivityResult Disposable Observer
        disposablePermissions()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearDisposable()
        isInit = false
    }

    @CallSuper
    override fun finish() {
        with(viewModel) {
            if (!checkBundleEnable()) return@with
            val reqCode = savedStateHandle.get<Int>(REQ_CODE) ?: -1
            val resCode = savedStateHandle.get<Int>(RES_CODE) ?: RESULT_CANCELED
            if (reqCode != -1) {
                val bundle = Bundle()
                bundle.putAll(getBundleData())
                intent.putExtras(bundle)
                setResult(resCode, intent)
            }
        }
        super.finish()
    }

    private fun performPermissions() {
        permissionDisposable = RxPermissionEvent.listen()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { permissionResult.launch(it.toTypedArray()) }
            .subscribe()
    }

    private fun disposablePermissions() {
        if (permissionDisposable != null) {
            permissionDisposable?.dispose()
            permissionDisposable = null
        }
    }

    /**
     * 기본 viewModels 와 같은 로직의 함수
     */
    protected inline fun <reified VM : ActivityViewModel> initViewModel(
        noinline extrasProducer: (() -> CreationExtras)? = null,
        noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
        val factoryPromise = factoryProducer ?: {
            defaultViewModelProviderFactory
        }

        return ViewModelLazy(
            VM::class,
            { viewModelStore },
            factoryPromise,
            { extrasProducer?.invoke() ?: this.defaultViewModelCreationExtras }
        )
    }

    /**
     * Activity DataBinding 처리 함수
     */
    private fun handleBinding() {
        binding = DataBindingUtil.setContentView<T>(this, layoutId).apply {
            lifecycleOwner = this@BaseActivity
            setVariable(bindingVariable, viewModel)
        }
    }

    /**
     * ActivityResult to Intent 변환 처리함수
     */
    private fun getActivityResultIntent(page: ActivityResult): Intent {
        return Intent(this, page.targetActivity.java).apply {
            if (page.flags != -1) {
                flags = page.flags
            }
            page.data.putInt(REQ_CODE, page.requestCode)
            putExtras(page.data)
        }
    }

    /**
     * Start Activity And Animation
     */
    private fun startActivityAndAnimation(result: ActivityResult) {
        val intent = getActivityResultIntent(result)
        if (result.requestCode != -1) {
            val options: ActivityOptionsCompat? =
                if (result.enterAni != -1 && result.exitAni != -1) {
                    ActivityOptionsCompat.makeCustomAnimation(
                        this,
                        result.enterAni,
                        result.exitAni
                    )
                } else {
                    null
                }
            activityResult.launch(intent, options)
        } else {
            startActivity(intent)
            if (result.enterAni != -1 && result.exitAni != -1) {
                overridePendingTransition(result.enterAni, result.exitAni)
            }
        }
    }
}
