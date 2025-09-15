package com.features.base_mvvm

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.features.base_mvvm.databinding.ARefactorBaseTestBinding
import com.features.base_mvvm_bottom_sheet_bridge.BaseMvvmBottomSheetBridge
import com.hmju.core.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

/**
 * Description : StartResult Test 용 액티비티
 *
 * Created by juhongmin on 2022/03/19
 */
@AndroidEntryPoint
class RefactorBaseTestActivity : BaseActivity<ARefactorBaseTestBinding, RefactorBaseTestViewModel>(
    R.layout.a_refactor_base_test
) {

    override val viewModel: RefactorBaseTestViewModel by initViewModel()
    override val bindingVariable: Int = BR.vm

    @Inject
    lateinit var bottomSheetBridge: BaseMvvmBottomSheetBridge

    companion object {
        val flow: Flow<Int> = flow {
            for (i in 0..100) {
                emit(i)
                delay(200)
                if (i == 20) {
                    error("Errorrororororo")
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 백그라운드에서 계속해서 진행 그러다 맨 마지막 값 가져옴
        // 1,2,3 ...11,12,13...
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.RESUMED) {
//                viewModel.testStateFlow.collectLatest {
//                    Timber.d("RepeatOnLifecycle $it")
//                }
//            }
//        }
        // 백그라운드로 나가면 코루틴 취소, 돌아오면 다시 시작
        // 1,2,3 ... 4,5,6
//        lifecycleScope.launchWhenResumed {
//            viewModel.testStateFlow.collectLatest { Timber.d("LaunchWhenResume $it") }
//        }

        // 백그라운드에서 계속해서 진행 다시 시작
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.RESUMED) {
//                flow.collectLatest { Timber.d("RepeatOnLifecycle $it") }
//            }
//        }

        // lifecycleScope.launchWhenResumed { flow.collectLatest { Timber.d("LaunchWhenResume $it") } }

        flow.catch { Timber.d("ERROR $it") }
            .flatMapConcat { flowOf(it) }
            .onEach { Timber.d("JJJ $it") }
            .launchIn(lifecycleScope)

        binding.tvBottomSheet.setOnClickListener {
            bottomSheetBridge.showBottomSheet(supportFragmentManager)
        }
        binding.tvShareBottomSheet.setOnClickListener {
            bottomSheetBridge.showShareBottomSheet(supportFragmentManager)
        }
    }
}
