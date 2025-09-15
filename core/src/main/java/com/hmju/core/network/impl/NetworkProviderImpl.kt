package com.hmju.core.network.impl

import com.hmju.core.BuildConfig
import com.hmju.core.network.NetworkProvider
import com.hmju.core.network.adapter.CoroutineErrorHandlingCallAdapter
import com.hmju.core.network.adapter.RxErrorHandlingCallAdapter
import com.hmju.core.network.qualifiers.ApiHttpClient
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject

/**
 * Description : Network Module impl 클래스
 *
 * Created by juhongmin on 2023/03/27
 */
@ExperimentalSerializationApi
internal class NetworkProviderImpl @Inject constructor(
    json: Json,
    @ApiHttpClient httpClient: OkHttpClient,
) : NetworkProvider {

    private val apiRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxErrorHandlingCallAdapter.create())
            .addCallAdapterFactory(CoroutineErrorHandlingCallAdapter.create())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    override fun <T> createApiService(service: Class<T>): T {
        return apiRetrofit.create(service)
    }
}
