package com.hmju.core.network.di

import com.hmju.core.login_manager.LoginManager
import com.hmju.core.login_manager.di.LoginManagerModule
import com.hmju.core.network.CommonApiService
import com.hmju.core.network.AuthManager
import com.hmju.core.network.NetworkConfig
import com.hmju.core.network.NetworkProvider
import com.hmju.core.network.adapter.PauseAbleThreadPoolExecutor
import com.hmju.core.network.impl.AuthManagerImpl
import com.hmju.core.network.impl.NetworkProviderImpl
import com.hmju.core.network.interceptor.HeaderInterceptor
import com.hmju.core.network.interceptor.TokenAuthenticator
import com.hmju.core.network.qualifiers.*
import com.hmju.core.pref.PreferenceManager
import com.hmju.core.pref.di.PreferenceManagerModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hmju.http.tracking_interceptor.TrackingHttpInterceptor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Description : Network Module
 *
 * Created by juhongmin on 2023/03/27
 */
@InstallIn(SingletonComponent::class)
@Module(includes = [LoginManagerModule::class, PreferenceManagerModule::class])
internal object RemoteModule {

    @Singleton
    @Provides
    fun provideJsonFormat(): Json = Json {
        isLenient = true // Json 큰따옴표 느슨하게 체크.
        ignoreUnknownKeys = true // Field 값이 없는 경우 무시
        coerceInputValues = true // "null" 이 들어간경우 default Argument 값으로 대체
    }

    @Singleton
    @Provides
    @ApiHeaderInterceptor
    fun provideHeaderInterceptor(
        loginManager: LoginManager
    ): Interceptor = HeaderInterceptor(loginManager)

    @Singleton
    @Provides
    @TrackingInterceptor
    fun provideTrackingInterceptor(): Interceptor = TrackingHttpInterceptor()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor(
        logger = {
            Timber.tag("HTTP_LOG").d(it)
        }
    ).apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun bindAuthManager(
        prefManager: PreferenceManager,
        @TrackingInterceptor trackingInterceptor: Interceptor,
        loginInterceptor: HttpLoggingInterceptor
    ): AuthManager {
        return AuthManagerImpl(prefManager, trackingInterceptor, loginInterceptor)
    }

    @Singleton
    @Provides
    fun provideTokenAuthenticator(
        loginManager: LoginManager,
        authManager: AuthManager
    ): Authenticator = TokenAuthenticator(loginManager, authManager)

    @Singleton
    @Provides
    fun provideDispatcher(
        loginManager: LoginManager,
        authManager: AuthManager
    ): Dispatcher = Dispatcher(
        PauseAbleThreadPoolExecutor(loginManager, authManager)
    )

    @Singleton
    @Provides
    @ApiHttpClient
    fun provideHttpClient(
        @ApiHeaderInterceptor headerInterceptor: Interceptor,
        @TrackingInterceptor trackingInterceptor: Interceptor,
        tokenAuthenticator: Authenticator,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        dispatcher: Dispatcher
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(NetworkConfig.CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(NetworkConfig.READ_TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(NetworkConfig.WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
            .dispatcher(dispatcher)
            .authenticator(tokenAuthenticator)
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(trackingInterceptor)
            .build()
    }

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideNetwork(
        json: Json,
        @ApiHttpClient httpClient: OkHttpClient
    ): NetworkProvider {
        return NetworkProviderImpl(json, httpClient)
    }

    @Provides
    fun provideApiService(
        provider: NetworkProvider
    ): CommonApiService {
        return provider.createApiService(CommonApiService::class.java)
    }
}
