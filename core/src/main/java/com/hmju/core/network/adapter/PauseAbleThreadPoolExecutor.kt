package com.hmju.core.network.adapter

import com.hmju.core.model.auth.TokenEntity
import com.hmju.core.model.base.JSendObj
import com.hmju.core.pref.PreferenceManager
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.http.POST
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock


/**
 * Description : 특정 타임때 쓰레드 중단 했다가 재게 처리하는 클래스
 *
 * Created by juhongmin on 11/25/23
 */
class PauseAbleThreadPoolExecutor constructor(
    private val prefManager: PreferenceManager,
    private val retrofit: Retrofit,
) : ThreadPoolExecutor(
    0,
    4,
    60,
    TimeUnit.SECONDS,
    SynchronousQueue()
) {

    private val json: Json by lazy {
        Json {
            isLenient = true // Json 큰따옴표 느슨하게 체크.
            ignoreUnknownKeys = true // Field 값이 없는 경우 무시
            coerceInputValues = true // "null" 이 들어간경우 default Argument 값으로 대체
        }
    }

    private var isPaused = false
    private val pauseLock = ReentrantLock()
    private val unPaused: Condition = pauseLock.newCondition()

    interface ApiService {
        @POST("/api/til/auth/refresh")
        fun fetch(): Single<JSendObj<TokenEntity>>
    }

    private val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }

    private val dateFormat: SimpleDateFormat by lazy { SimpleDateFormat("yyyy년 MM월 dd일 kk시 mm분 ss초") }

    override fun beforeExecute(t: Thread, r: Runnable) {
        super.beforeExecute(t, r)
        pauseLock.lock()
        val currTime = System.currentTimeMillis()
        val expiredTime = prefManager.getLong(PreferenceManager.KEY_TOKEN_EXPIRED_MS)
        if (expiredTime.minus(30_000) <= currTime || expiredTime == 0L) {
            Timber.d("토큰이 만료 되었습니다. ${t.id}")
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

    private fun handleTokenRefresh() {
        val res = apiService.fetch().delay(3000, TimeUnit.MILLISECONDS)
            .blockingGet()
        val expiredTimeMs = res.payload.getPayload(json).getExpiredMs()
        prefManager.setValue(PreferenceManager.KEY_TOKEN_EXPIRED_MS, expiredTimeMs)
    }
}