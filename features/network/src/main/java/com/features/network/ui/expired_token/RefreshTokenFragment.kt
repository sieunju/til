package com.features.network.ui.expired_token

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.features.network.BR
import com.features.network.R
import com.features.network.databinding.FRefreshTokenBinding
import com.features.network.usecase.GetJSendListUseCase
import com.features.network.usecase.GetJSendListWithMetaUseCase
import com.features.network.usecase.GetJSendUseCase
import com.features.network.usecase.GetJSendWithMetaUseCase
import com.hmju.core.login_manager.LoginManager
import com.hmju.core.ui.base.BaseFragment
import com.hmju.core.ui.base.FragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import java.util.concurrent.TimeUnit
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

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    @Inject
    lateinit var getJSendUseCase: GetJSendUseCase

    @Inject
    lateinit var getJSendWithMetaUseCase: GetJSendWithMetaUseCase

    @Inject
    lateinit var getJSendListUseCase: GetJSendListUseCase

    @Inject
    lateinit var getJSendListWithMetaUseCase: GetJSendListWithMetaUseCase

    @Inject
    lateinit var loginManager: LoginManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DataBindingUtil.bind<FRefreshTokenBinding>(view)?.run {

            performance.setOnClickListener {
                multiApiInterval()
            }
        }
    }

    private fun multiApiInterval() {
        Flowable.interval(0, 100, TimeUnit.MILLISECONDS)
            .onBackpressureBuffer()
            .take(10)
            .flatMap { multiApi() }
            .doOnComplete {
                Timber.d("MultiApi SUCCESS")
            }
            .doOnError { Timber.d("ERROR $it") }
            .subscribe().addTo(compositeDisposable)
    }

    private fun multiApi(): Flowable<Any> {
        return Single.mergeDelayError(
            getJSendUseCase(),
            getJSendWithMetaUseCase(),
            getJSendListUseCase(),
            getJSendListWithMetaUseCase()
        )
    }
}
