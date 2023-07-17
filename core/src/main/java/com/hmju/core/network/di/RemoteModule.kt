package com.hmju.core.network.di

import com.hmju.core.login_manager.LoginManager
import com.hmju.core.network.NetworkConfig
import com.hmju.core.network.interceptor.HeaderInterceptor
import com.hmju.core.network.interceptor.RefreshTokenInterceptor
import com.hmju.core.network.interceptor.TokenAuthenticator
import com.hmju.core.network.qualifiers.*
import com.http.tracking_interceptor.TrackingHttpInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Description : Network Module
 *
 * Created by juhongmin on 2023/03/27
 */
@InstallIn(SingletonComponent::class)
@Module
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
    @HeaderJsonInterceptor
    fun provideHeaderInterceptor(
        loginManager: LoginManager
    ): Interceptor = HeaderInterceptor(loginManager)

    @Singleton
    @Provides
    @RefreshTokenJsonInterceptor
    fun provideRefreshTokenInterceptor(): Interceptor = RefreshTokenInterceptor()

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
    @TokenHttpClient
    fun provideTokenHttpClient(
        @RefreshTokenJsonInterceptor headerInterceptor: Interceptor,
        @TrackingInterceptor trackingInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(NetworkConfig.CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout(NetworkConfig.READ_TIME_OUT, TimeUnit.MILLISECONDS)
        .writeTimeout(NetworkConfig.WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(trackingInterceptor)
        .addInterceptor(headerInterceptor)
        .build()

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    @TokenRetrofit
    fun provideTokenRetrofit(
        @TokenHttpClient httpClient: OkHttpClient,
        json: Json
    ): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkConfig.BASE_URL)
        .client(httpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Singleton
    @Provides
    fun provideTokenAuthenticator(
        loginManager: LoginManager,
        @TokenRetrofit retrofit: Retrofit
    ): Authenticator = TokenAuthenticator(loginManager, retrofit)

    @Singleton
    @Provides
    @ApiHttpClient
    fun provideHttpClient(
        @HeaderJsonInterceptor headerInterceptor: Interceptor,
        @TrackingInterceptor trackingInterceptor: Interceptor,
        tokenAuthenticator: Authenticator,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(NetworkConfig.CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
        .readTimeout(NetworkConfig.READ_TIME_OUT, TimeUnit.MILLISECONDS)
        .writeTimeout(NetworkConfig.WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(trackingInterceptor)
        .addInterceptor(headerInterceptor)
        .authenticator(tokenAuthenticator)
        .build()

//    @ExperimentalSerializationApi
//    @Provides
//    fun provideRetrofit(
//        @ApiHttpClient httpClient: OkHttpClient,
//        json: Json
//    ): Retrofit = Retrofit.Builder()
//        .baseUrl(NetworkConfig.BASE_URL)
//        .client(httpClient)
//        .addCallAdapterFactory(RxErrorHandlingCallAdapter.create())
//        .addCallAdapterFactory(CoroutineErrorHandlingCallAdapter.create())
//        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
//        .build()
}