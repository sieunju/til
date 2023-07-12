package com.features.network.ui.expired_token

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hmju.core.ui.base.BaseFragment
import com.features.network.BR
import com.features.network.R
import com.features.network.databinding.FRefreshTokenBinding
import com.features.network.usecase.*
import com.hmju.core.login_manager.LoginManager
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

/**
 * Description : 토큰 만료에 대한 처리 Fragment
 *
 * Created by juhongmin on 2022/01/12
 */
@AndroidEntryPoint
class RefreshTokenFragment :
    BaseFragment<FRefreshTokenBinding, RefreshTokenFragmentViewModel>(R.layout.f_refresh_token) {

    override val viewModel: RefreshTokenFragmentViewModel by initViewModel()

    override val bindingVariable: Int = BR.vm

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    @Inject
    lateinit var getRefreshTokenUseCase: GetRefreshTokenUseCase

    @Inject
    lateinit var getExpiredTokenUseCase: GetExpiredTokenUseCase

    @Inject
    lateinit var getTestUseCase: GetTestUseCase

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
            refresh.setOnClickListener {
                getRefreshTokenUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Timber.d("Response $it")
                        loginManager.setToken(it.token)
                    }, {
                        Timber.e("Error $it")
                    }).addTo(compositeDisposable)
            }

            test.setOnClickListener {
                getTestUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Timber.d("Response $it")
                    }, {
                        Timber.e("Error $it")
                    }).addTo(compositeDisposable)
            }

            expired.setOnClickListener {
                getExpiredTokenUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Timber.d("Response $it")
                        loginManager.setToken(it.token)
                    }, {
                        Timber.e("Error $it")
                    }).addTo(compositeDisposable)
            }

            performance.setOnClickListener {
                multiApiInterval()
            }
        }
    }

    private fun expiredToken() {
        getExpiredTokenUseCase()
            .subscribe({
                loginManager.setToken(it.token)
            }, {

            }).addTo(compositeDisposable)
    }

    private fun multiApiInterval() {
        Flowable.interval(0, 100, TimeUnit.MILLISECONDS)
            .doOnSubscribe {
                if (Random.nextBoolean()) {
                    expiredToken()
                }
            }
            .onBackpressureBuffer()
            .take(10)
            .flatMap { multiApi() }
            .doOnComplete {
                Timber.d("MultiApi SUCCESS")
            }
            .subscribe({

            }, {
                Timber.e("Error $it")
            }).addTo(compositeDisposable)
    }

    private fun multiApi(): Flowable<Any> {
        return Single.merge(
            getJSendUseCase(),
            getJSendWithMetaUseCase(),
            getJSendListUseCase(),
            getJSendListWithMetaUseCase()
        )
    }
}
