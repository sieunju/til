package com.hmju.core.repository

import com.hmju.core.model.auth.TokenEntity
import com.hmju.core.model.base.JSendObj
import io.reactivex.rxjava3.core.Single

interface RefreshTokenRepository {
    fun fetch(): Single<JSendObj<TokenEntity>>
}