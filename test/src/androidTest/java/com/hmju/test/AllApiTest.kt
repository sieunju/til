package com.hmju.test

import com.hmju.core.models.base.getOrNull
import com.hmju.core.models.params.PagingQueryParams
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.reactivex.rxjava3.exceptions.UndeliverableException
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import java.net.SocketException
import javax.inject.Inject

/**
 * Description : 모든 API 호출 완료되는지 테스트하는 클래스
 *
 * Created by juhongmin on 2/18/24
 */
@HiltAndroidTest
class AllApiTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var asyncMigrateApis: com.features.async_migrate.ApiService

    @Inject
    lateinit var baseMvvmApis: com.features.base_mvvm.ApiService

    @Before
    fun init() {
        hiltRule.inject()
        initRxJava()
    }

    /**
     * reactivex.exceptions.UndeliverableException 처리 함수.
     */
    private fun initRxJava() {
        RxJavaPlugins.setErrorHandler { e ->
            var error = e
            if (error is UndeliverableException) {
                error = e.cause ?: Throwable("UndeliverableException")
            }
            if (error is SocketException || error is IOException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (error is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if (error is NullPointerException || error is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler?.uncaughtException(
                    Thread.currentThread(),
                    error
                )
                return@setErrorHandler
            }
            if (error is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler?.uncaughtException(
                    Thread.currentThread(),
                    error
                )
                return@setErrorHandler
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun 모든_API_전수검사테스트() {
        runTest {
            val job1 = backgroundScope.async { handleAsyncMigrate() }
            awaitAll(job1)
            println("SUCCCCC")
        }
    }

    private suspend fun handleAsyncMigrate() {
        val errorApis = mutableListOf<String>()
        try {
            asyncMigrateApis.fetchGoods(
                PagingQueryParams().getQueryMap()
            ).blockingGet()
        } catch (ex: Exception) {
            errorApis.add("fetchGoods")
        }

        val api2 = asyncMigrateApis.fetchCoGoods(
            PagingQueryParams().getQueryMap()
        ).getOrNull()
        if (api2 == null) {
            errorApis.add("fetchCoGoods")
        }
        println("AsyncMigrate ErrorList $errorApis")
    }
}