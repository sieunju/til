package com.hmju.core.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.bumptech.glide.Glide

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

    private val activityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val reqCode = result.data?.extras?.getInt(REQ_CODE) ?: -1
        viewModel.onActivityResult(reqCode, result.resultCode, result.data?.extras ?: Bundle())
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initBinding()

        with(viewModel) {
            setLifecycle(Lifecycle.Event.ON_CREATE)
            onDirectCreate()
            onIntent()
            startActivityPage.observe(this@BaseActivity) { startActivityAndAnimation(it) }
            startFinishEvent.observe(this@BaseActivity) { finish() }
        }
        // 추후 화면에 따라 StatusBar 컨트롤
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
    override fun onDestroy() {
        super.onDestroy()
        with(viewModel) {
            setLifecycle(Lifecycle.Event.ON_DESTROY)
            clearDisposable()
            clearRequestManager()
        }
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
    private fun initBinding() {
        binding = DataBindingUtil.setContentView<T>(this, layoutId).apply {
            lifecycleOwner = this@BaseActivity
            setVariable(bindingVariable, viewModel)
            viewModel.initRequestManager(Glide.with(this@BaseActivity))
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
            val options: ActivityOptionsCompat? = if (result.isValidateAni()) {
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
            if (result.isValidateAni()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    // 어떤 이유에서인지 몰라도 애니메이션 안먹힘 onCreate 에서 호출해야 됨
                    overrideActivityTransition(
                        Activity.OVERRIDE_TRANSITION_OPEN,
                        result.enterAni,
                        result.exitAni
                    )
                } else {
                    @Suppress("DEPRECATION")
                    overridePendingTransition(result.enterAni, result.exitAni)
                }
            }
        }
    }
}
