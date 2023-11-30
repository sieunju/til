package com.hmju.core.network.adapter

import com.hmju.core.model.auth.TokenEntity
import com.hmju.core.model.base.JSendObj
import com.hmju.core.network.NetworkConfig
import com.hmju.core.pref.PreferenceManager
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import timber.log.Timber
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock


/**
 * Description : HTTP 통신시 쓰레드 제어 하는 클래스
 *
 * Created by juhongmin on 11/25/23
 */
class PauseAbleThreadPoolExecutor constructor(
    private val prefManager: PreferenceManager,
    private val httpClient: OkHttpClient
) : ThreadPoolExecutor(
    0,
    4,
    60,
    TimeUnit.SECONDS,
    LinkedBlockingQueue(),
    CallerRunsPolicy()
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

    override fun beforeExecute(t: Thread, r: Runnable) {
        super.beforeExecute(t, r)
        pauseLock.lock()
        if (isCallRefreshToken()) {
            // println("================= 토큰을 재발급 합니다 ${t.name} ============================")
            Timber.tag("HTTP_LOG_")
                .d("================= 토큰을 재발급 합니다 ${t.name} ============================")
            handleTokenRefresh()
        } else {
            // println("================= 단순 API 호출합니다. ${t.name} ============================")
            Timber.tag("HTTP_LOG_").d("단순 API 호출합니다. ${t.name}")
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
     * 30초 단위
     * @return true, false
     */
    private fun isCallRefreshToken(): Boolean {
        val currTime = System.currentTimeMillis()
        val expiredTime = prefManager.getLong(PreferenceManager.KEY_TOKEN_EXPIRED_MS)
        return expiredTime.minus(30_000) <= currTime || expiredTime == 0L
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
            // 대기 중인 모든 스레드를 깨웁니다.
            // 이 조건을 기다리고 있는 스레드가 있으면 모두 깨어납니다.
            // 각 스레드는 Wait에서 반환되기 전에 잠금을 다시 획득해야 합니다.
            unPaused.signalAll()
        } finally {
            pauseLock.unlock()
        }
    }

    /**
     * 쓰레드 대기 하고 토큰 재발급 처리해서 제 셋팅하는 함수
     * 테스트용오르 JWT Decode 해서 만료 시간을 가져온후 해당 값을 SharedPreference 저장
     */
    private fun handleTokenRefresh() {
        isPaused = true
        val res = reqRefreshToken()
        val expiredTimeMs = res.payload.getPayload(json).getExpiredMs()
        prefManager.setValue(PreferenceManager.KEY_TOKEN_EXPIRED_MS, expiredTimeMs)
        prefManager.setValue(PreferenceManager.KEY_TOKEN,res.payload.token)
        isPaused = false
        unPaused.signalAll()
    }

    /**
     * Request API Refresh Token
     *
     * @return 재발급 받은 토큰 데이터 모델
     */
    @OptIn(ExperimentalSerializationApi::class)
    private fun reqRefreshToken(): JSendObj<TokenEntity> {
        val body = JSONObject()
        body.put("email", "j.sieun@gmail.com")
        body.put("delay", 2000)
        body.put("expiredTime", "1m")
        val req = Request.Builder()
            .url(NetworkConfig.BASE_URL.plus("/api/til/auth/refresh"))
            .post(body.toString().toRequestBody())
            .build()
        val res = httpClient.newCall(req).execute()
        return json.decodeFromString(res.body?.string()!!)
    }
}