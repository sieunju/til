package com.hmju.core.network.di

import com.hmju.core.network.NetworkProvider
import com.hmju.core.network.impl.NetworkProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [RemoteModule::class])
internal abstract class NetworkModule {
    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Binds
    abstract fun bindNetwork(impl: NetworkProviderImpl): NetworkProvider
}
