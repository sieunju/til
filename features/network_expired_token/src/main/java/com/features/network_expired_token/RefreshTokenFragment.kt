package com.features.network_expired_token

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.features.network_expired_token.databinding.FRefreshTokenBinding
import com.features.network_expired_token.usecase.GetJSendListUseCase
import com.features.network_expired_token.usecase.GetJSendListWithMetaUseCase
import com.features.network_expired_token.usecase.GetJSendUseCase
import com.features.network_expired_token.usecase.GetJSendWithMetaUseCase
import com.hmju.core.ui.base.BaseFragment
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

/**
 * Description : 토큰 만료에 대한 처리 Fragment
 *
 * Created by juhongmin on 2022/01/12
 */
@AndroidEntryPoint
class RefreshTokenFragment :
    BaseFragment<FRefreshTokenBinding, FragmentViewModel>(R.layout.f_refresh_token) {

    override val viewModel: FragmentViewModel by initViewModel()

    override val bindingVariable: Int = BR.vm

    @Inject
    lateinit var getJSendUseCase: GetJSendUseCase

    @Inject
    lateinit var getJSendWithMetaUseCase: GetJSendWithMetaUseCase

    @Inject
    lateinit var getJSendListUseCase: GetJSendListUseCase

    @Inject
    lateinit var getJSendListWithMetaUseCase: GetJSendListWithMetaUseCase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.bind<FRefreshTokenBinding>(view)?.run {

            performance.setOnClickListener {
                multiApiInterval()
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun multiApiInterval() {
        (0..9)
            .asFlow()
            .onEach { delay(100) }
            .flatMapConcat { flow { emit(multiApi()) } }
            .onCompletion { err ->
                if (err == null) {
                    Timber.d("MultiApi SUCCESS")
                } else {
                    Timber.d("ERROR $err")
                }
            }
            .launchIn(lifecycleScope)
    }

    private suspend fun multiApi(): List<Any> {
        val jsendDeferred = lifecycleScope.async { getJSendUseCase() }
        val jsendMetaDeferred = lifecycleScope.async { getJSendWithMetaUseCase() }
        val jsendListDeferred = lifecycleScope.async { getJSendListUseCase() }
        val jsendListMetaDeferred = lifecycleScope.async { getJSendListWithMetaUseCase() }

        return listOf(
            jsendDeferred,
            jsendMetaDeferred,
            jsendListDeferred,
            jsendListMetaDeferred
        ).awaitAll()
    }
}
