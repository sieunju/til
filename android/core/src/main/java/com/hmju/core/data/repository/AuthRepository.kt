package com.hmju.core.data.repository

import com.hmju.core.data.model.auth.TokenEntity
import com.hmju.core.data.model.base.JSendObj
import io.reactivex.rxjava3.core.Single

/**
 * Description : 사용자 인증 Repository Class
 *
 * Created by juhongmin on 2022/01/12
 */
interface AuthRepository {
    fun tokenRefresh(): Single<JSendObj<TokenEntity>>
    fun tokenExpired(): Single<JSendObj<TokenEntity>>
}