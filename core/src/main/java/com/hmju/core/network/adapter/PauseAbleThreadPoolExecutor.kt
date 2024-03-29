package com.hmju.core.network.adapter

import com.hmju.core.login_manager.LoginManager
import com.hmju.core.models.auth.AuthTokenEntity
import com.hmju.core.network.AuthManager
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import kotlin.math.pow


/**
 * Description : HTTP 통신시 쓰레드 제어 하는 클래스
 *
 * Created by juhongmin on 11/25/23
 */
internal class PauseAbleThreadPoolExecutor(
    private val loginManager: LoginManager,
    private val authManager: AuthManager
) : ThreadPoolExecutor(
    4,
    Int.MAX_VALUE,
    60,
    TimeUnit.SECONDS,
    LinkedBlockingQueue(),
    CallerRunsPolicy()
) {

    private var isPaused = false
    private val pauseLock = ReentrantLock()
    private val unPaused: Condition = pauseLock.newCondition()

    override fun beforeExecute(t: Thread, r: Runnable) {
        super.beforeExecute(t, r)
        pauseLock.lock()
        if (isCallRefreshToken()) {
            Timber.tag("Network_Test").w("Dispatcher 토큰을 재발급합니다. ${t.name}")
            handleTokenRefresh()
        }

        try {
            while (isPaused) {
                unPaused.await()
            }
        } catch (e: InterruptedException) {
            Timber.d("ERROR? $e")
            t.interrupt()
        } finally {
            pauseLock.unlock()
        }
        // 기존 로직 참고용
        /* pauseLock.lock()
        if (isCallRefreshToken()) {
            // pause()
            isPaused = true
            handleTokenRefresh()
            isPaused = false
            // resume()
        }
        try {
            while (isPaused) {
                unPaused.await()
            }
        } catch (ie: InterruptedException) {
            t.interrupt()
        } finally {
            pauseLock.unlock()
        }
         */
    }

    /**
     * 토큰 만료 됐는지 상태 체크하는 함수
     * N초 단위
     * @return true, false
     */
    private fun isCallRefreshToken(): Boolean {
        val currTime = System.currentTimeMillis()
        val expiredTime = loginManager.getTokenExpiredMs()
        return expiredTime.minus(5_000) <= currTime || expiredTime == 0L
    }

    fun pause() {
        pauseLock.lock()
        try {
            isPaused = true
        } finally {
            pauseLock.unlock()
        }
    }

    fun resume() {
        pauseLock.lock()
        try {
            isPaused = false
            unPaused.signalAll()
        } finally {
            pauseLock.unlock()
        }
    }

    /**
     * 쓰레드 대기 하고 토큰 재발급 처리해서 제 셋팅하는 함수
     * 테스트용오르 JWT Decode 해서 만료 시간을 가져온후 해당 값을 SharedPreference 저장
     */
    @Synchronized
    private fun handleTokenRefresh() {
        try {
            isPaused = true
            if (authManager.isRefreshToken()) {
                val entity = reqRetryRefreshToken().blockingGet()
                loginManager.setToken(entity.token)
                loginManager.setRefreshToken(entity.refreshToken)
            } else {
                val entity = authManager.createToken()
                loginManager.setToken(entity.token)
                loginManager.setRefreshToken(entity.refreshToken)
            }
        } catch (ex: Exception) {
            Timber.tag("Network_Test").d("handleTokenRefresh Error $ex")
        } finally {
            isPaused = false
            // 대기 중인 모든 스레드를 깨웁니다.
            // 이 조건을 기다리고 있는 스레드가 있으면 모두 깨어납니다.
            // 각 스레드는 Wait에서 반환되기 전에 잠금을 다시 획득해야 합니다.
            unPaused.signalAll()
        }
    }

    /**
     * 토큰 갱신 API 실패시 재시도 하는 함수
     */
    private fun reqRetryRefreshToken(): Single<AuthTokenEntity> {
        return Single.create { emitter ->
            try {
                val res = authManager.refreshToken()
                emitter.onSuccess(res)
            } catch (ex: Exception) {
                emitter.onError(ex)
            }
        }.compose(applyRetryPolicy()).subscribeOn(Schedulers.io())
    }

    /**
     * 재시도 N 번 처리하는 함수
     */
    private fun <T : Any> applyRetryPolicy(
        maxRetries: Int = 3
    ) = SingleTransformer<T, T> { single ->
        single.retryWhen { errors ->
            errors.zipWith(
                Flowable.range(1, maxRetries + 1)
            ) { error, retryCount -> Pair(error, retryCount) }
                .flatMap { (err, retryCount) ->
                    // 최대 maxRetries번까지 재시도
                    if (retryCount <= maxRetries) {
                        // 지수 백오프 정책을 적용하여 재시도 간격을 증가시킴
                        val delayMillis = 2.0.pow(retryCount.toDouble()).toLong() * 1000
                        Flowable.timer(delayMillis, TimeUnit.MILLISECONDS)
                    } else {
                        // 재시도 횟수 초과 또는 에러가 기타 경우에는 에러 전파
                        Flowable.error(err)
                    }
                }
        }
    }
}
