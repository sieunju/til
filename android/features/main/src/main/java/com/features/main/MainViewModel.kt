package com.features.main

import com.features.base_mvvm_requirements.BaseMvvmRequirements
import com.features.core_ui.base.ActivityViewModel
import com.features.core_ui.lifecycle.OnCreated
import com.features.core_ui.lifecycle.OnIntent
import com.features.network_requirements.NetworkRequirements
import com.features.recyclerview_requirements.RecyclerViewRequirements
import com.hmju.core.login_manager.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

/**
 * Description :
 *
 * Created by juhongmin on 2022/02/26
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkRequirements: NetworkRequirements,
    private val recyclerViewRequirements: RecyclerViewRequirements,
    private val mvvmRequirements: BaseMvvmRequirements,
    private val loginManager: LoginManager,
) : ActivityViewModel() {

    @OnIntent
    fun intentData() {
        Timber.d("[s] onCreate Intent Data ===============================================")
        savedStateHandle.keys().forEach {
            Timber.d("Key $it Value ${savedStateHandle.get<Any>(it)}")
        }
        Timber.d("[s] onCreate Intent Data ===============================================")
    }

    @OnCreated
    fun randomLogin() {
        Flowable.interval(3000, TimeUnit.MILLISECONDS)
            .subscribe({
                loginManager.setToken(if (Random.nextInt(100) > 30) "Token" else "")
                // Timber.d("isLoginCheck ${loginManager.isLogin()}")
            }, {

            }).addTo(compositeDisposable)
    }

    fun moveToNetworkPage() {
        networkRequirements.moveToNetworkPage()
    }

    fun moveToRecyclerViewPage() {
        recyclerViewRequirements.moveToRecyclerViewPage()
    }

    fun moveToMvvmLifecyclePage() {
        mvvmRequirements.moveToBaseMvvm()
    }
}
