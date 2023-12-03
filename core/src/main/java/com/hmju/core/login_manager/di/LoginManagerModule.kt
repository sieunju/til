package com.hmju.core.login_manager.di

import com.hmju.core.login_manager.LoginManager
import com.hmju.core.login_manager.LoginManagerImpl
import com.hmju.core.pref.di.PreferenceManagerModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Description : 로그인 메니저 모듈
 *
 * Created by juhongmin on 2022/01/12
 */
@InstallIn(SingletonComponent::class)
@Module(includes = [PreferenceManagerModule::class])
internal abstract class LoginManagerModule {
    @Singleton
    @Binds
    abstract fun bindLoginManager(impl: LoginManagerImpl): LoginManager
}
